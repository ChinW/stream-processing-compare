## Stream-Processing-Compare

Currently, it tests Flink and Jet.

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

Log:
```aidl
❯ flink run -c chiw.spc.flink.FlinkWordCountKt stream-processing-compare-1.0-SNAPSHOT.jar
Job has been submitted with JobID 8ee33e2a1b8c633ed8fb354434bbc22f
Program execution finished
Job with JobID 8ee33e2a1b8c633ed8fb354434bbc22f has finished.
Job Runtime: 87861 ms
```