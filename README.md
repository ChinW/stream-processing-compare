## Stream-Processing-Compare

Currently, it tests Flink and Jet.

### The Tested WordCount Pipeline

```aidl
Source (read from file, 5MB)
 -> Process: Split line into words (Here here is a bomb, every word emit 1000 times)
 -> Group/Count
 -> Sink (do nothing)
```

### Run Jet

- Directly run JetWordCount

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
flink run -c chiw.spc.flink.FlinkWordCountKt stream-processing-compare-1.0-SNAPSHOT.jar
```

### Reference

#### My Local Result
- MacBook Pro (13-inch, 2020, Four Thunderbolt 3 ports)
- 2 GHz Quad-Core Intel Core i5 (8 logic processors)
- 16 GB 3733 MHz LPDDR4X
- JDK 11

##### Jet 4.4
Pipeline:
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
```aidl
Jet: finish in 36.45935081 seconds.
```
##### Flink 1.12.2 for Scala 2.11
`flink-config.yaml` Configuration:
```aidl
jobmanager.rpc.address: localhost
jobmanager.rpc.port: 6123
jobmanager.memory.process.size: 2096m
taskmanager.memory.process.size: 12288m
taskmanager.numberOfTaskSlots: 8
parallelism.default: 8
```

Pipeline:
```aidl
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
```aidl
❯ flink run -c chiw.spc.flink.FlinkWordCountKt stream-processing-compare-1.0-SNAPSHOT.jar
Job has been submitted with JobID 163ce849a663e45f3c3028a98f260e7c
Program execution finished
Job with JobID 163ce849a663e45f3c3028a98f260e7c has finished.
Job Runtime: 88614 ms

❯ flink run -c chiw.spc.flink.FlinkWordCountKt stream-processing-compare-1.0-SNAPSHOT.jar
Job has been submitted with JobID fcf12488204969299e4e5d7f23f4ea6e
Program execution finished
Job with JobID fcf12488204969299e4e5d7f23f4ea6e has finished.
Job Runtime: 90165 ms

❯ flink run -c chiw.spc.flink.FlinkWordCountKt stream-processing-compare-1.0-SNAPSHOT.jar
Job has been submitted with JobID 37e349e4fad90cd7405546d30239afa4
Program execution finished
Job with JobID 37e349e4fad90cd7405546d30239afa4 has finished.
Job Runtime: 78908 ms
```