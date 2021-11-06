## Stream-Processing-Compare

Currently, it tests Flink and Jet.

### The Tested Pipeline

#### WordCount Bomb
```
Source (read from file, 5MB)
 -> Process: Split line into words (Here here is a bomb, every word emit 1000 times)
 -> Group/Count
 -> Sink (do nothing)
```

#### Order Bomb

This pipeline measures 2 serializations:
- Hazelcast Portable vs Flink TypeInformation 
- Protobuf

```
Source (remote DB, 10k items)
 -> Process 1: each item emit 1000 times, emit payload Tuple2<Other Item, Item>
 -> Process 2: do nothing, transfer the second item of payload
 -> Sink (remote DB)
```

### Build Proto

```
 protoc -I=./proto --java_out=./src/main/java ./proto/*  
 ```

### Run Jet

- JetWordCount.kt
- JetOrderPortableBomb.kt
- JetOrderMsgBomb.kt

### Run Flink
- Download flink
    - At https://apache.website-solution.net/flink/flink-1.12.2/flink-1.12.2-bin-scala_2.11.tgz
    - Or check out https://flink.apache.org/downloads.html
- Run flink cluster
    - Edit the `flink-config.yaml` if need
    - Run `start-cluster.sh`
- Build the library via gradle
- Submit job via either flink web page or below command:
```bash
flink run -c chiw.spc.flink.FlinkWordCountKt ./build/libs/stream-processing-compare-1.0-SNAPSHOT.jar
flink run -c chiw.spc.flink.FlinkOrderPortableBombKt ./build/libs/stream-processing-compare-1.0-SNAPSHOT.jar
flink run -c chiw.spc.flink.FlinkOrderMsgBombKt ./build/libs/stream-processing-compare-1.0-SNAPSHOT.jar
```

### Reference

#### My Local Result
- MacBook Pro (13-inch, 2020, Four Thunderbolt 3 ports)
- 2 GHz Quad-Core Intel Core i5 (8 logic processors)
- 16 GB 3733 MHz LPDDR4X
- JDK 11

##### Jet 4.4
WordCount Pipeline:
```
digraph DAG {
	"items" [localParallelism=1];
	"fused(flat-map, filter)" [localParallelism=8];
	"group-and-aggregate-prepare" [localParallelism=8];
	"group-and-aggregate" [localParallelism=8];
	"do-nothing-sink" [localParallelism=1];
	"items" -> "fused(flat-map, filter)" [queueSize=1024];
	"fused(flat-map, filter)" -> "group-and-aggregate-prepare" [label="partitioned", queueSize=1024];
	subgraph cluster_0 {
		"group-and-aggregate-prepare" -> "group-and-aggregate" [label="distributed-partitioned", queueSize=1024];
	}
	"group-and-aggregate" -> "do-nothing-sink" [queueSize=1024];
}
```

Log:
```
## WordCount
Start time: 2021-04-18T13:52:52.106
Duration: 00:00:36.459
Jet: finish in 36.45935081 seconds.

Start time: 2021-04-19T16:51:53.806
Duration: 00:00:30.143
Jet: finish in 30.625740453 seconds.

Start time: 2021-04-19T16:52:48.906
Duration: 00:00:37.207
Jet: finish in 37.862554137 seconds.

## OrderPortable Bomb
Start time: 2021-04-30T16:11:49.324
Duration: 00:00:21.869
Jet: finish in 22.105343252 seconds.

## OrderMsg Bomb
Start time: 2021-04-30T16:10:23.867
Duration: 00:00:08.634
Jet: finish in 8.805681357 seconds.
```
##### Flink 1.12.2 for Scala 2.11
`flink-config.yaml` Configuration:
```
jobmanager.rpc.address: localhost
jobmanager.rpc.port: 6123
jobmanager.memory.process.size: 2096m
taskmanager.memory.process.size: 12288m
taskmanager.numberOfTaskSlots: 8
parallelism.default: 8
```

WordCount Pipeline:
```
{
  "nodes" : [ {
    "id" : 1,
    "type" : "Source: Custom Source",
    "pact" : "Data Source",
    "contents" : "Source: Custom Source",
    "parallelism" : 1
  }, {
    "id" : 2,
    "type" : "Flat Map",
    "pact" : "Operator",
    "contents" : "Flat Map",
    "parallelism" : 8,
    "predecessors" : [ {
      "id" : 1,
      "ship_strategy" : "REBALANCE",
      "side" : "second"
    } ]
  }, {
    "id" : 4,
    "type" : "Keyed Aggregation",
    "pact" : "Operator",
    "contents" : "Keyed Aggregation",
    "parallelism" : 8,
    "predecessors" : [ {
      "id" : 2,
      "ship_strategy" : "HASH",
      "side" : "second"
    } ]
  }, {
    "id" : 5,
    "type" : "Sink: Unnamed",
    "pact" : "Data Sink",
    "contents" : "Sink: Unnamed",
    "parallelism" : 8,
    "predecessors" : [ {
      "id" : 4,
      "ship_strategy" : "FORWARD",
      "side" : "second"
    } ]
  } ]
}
```

Log:
```
❯ flink run -c chiw.spc.flink.FlinkWordCountKt stream-processing-compare-1.0-SNAPSHOT.jar
Job with JobID 163ce849a663e45f3c3028a98f260e7c has finished.
Job Runtime: 88614 ms

❯ flink run -c chiw.spc.flink.FlinkWordCountKt stream-processing-compare-1.0-SNAPSHOT.jar
Job with JobID fcf12488204969299e4e5d7f23f4ea6e has finished.
Job Runtime: 90165 ms

❯ flink run -c chiw.spc.flink.FlinkWordCountKt stream-processing-compare-1.0-SNAPSHOT.jar
Job with JobID 37e349e4fad90cd7405546d30239afa4 has finished.
Job Runtime: 78908 ms

❯ flink run -c chiw.spc.flink.FlinkOrderMsgBombKt ./build/libs/stream-processing-compare-1.0-SNAPSHOT.jar
Job with JobID 8f9af15e547bdb9a70101f1e348fd102 has finished.
Job Runtime: 44215 ms

❯ flink run -c chiw.spc.flink.FlinkOrderMsgBombKt ./build/libs/stream-processing-compare-1.0-SNAPSHOT.jar
Job with JobID 0d335ed2e49ca8c0be93b1e837f37546 has finished.
Job Runtime: 50924 ms
```