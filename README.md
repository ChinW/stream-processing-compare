## Stream-Processing-Compare

Currently it tests Flink and Jet.

### Run Jet

- Directly run JetWordCount

### Run Flink

- Run flink cluster
    - Edit the configuration if need
    - Run `start-cluster.sh`
- Build the library via gradle
- Submit job via either flink web page or below command:
```bash
flink run -c chiw.spc.flink.FlinkWordCountKt stream-processing-compare-1.0-SNAPSHOT.jar
```

### Reference

#### My Local Result
MacBook Pro (13-inch, 2020, Four Thunderbolt 3 ports)
- 2 GHz Quad-Core Intel Core i5 (8 logic processors)
- 16 GB 3733 MHz LPDDR4X
  

##### Jet
```aidl
Jet: finish in 36.45935081 seconds.
```
##### Flink
```aidl
❯ flink run -c chiw.spc.flink.FlinkWordCountKt stream-processing-compare-1.0-SNAPSHOT.jar
Job has been submitted with JobID 8ee33e2a1b8c633ed8fb354434bbc22f
Program execution finished
Job with JobID 8ee33e2a1b8c633ed8fb354434bbc22f has finished.
Job Runtime: 87861 ms
```