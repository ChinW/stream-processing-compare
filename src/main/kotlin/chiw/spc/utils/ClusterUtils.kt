package chiw.spc.utils

import chiw.spc.types.DataMap
import com.hazelcast.client.HazelcastClient
import com.hazelcast.client.config.ClientConfig
import com.hazelcast.client.config.YamlClientConfigBuilder
import com.hazelcast.config.YamlConfigBuilder
import com.hazelcast.core.HazelcastInstance
import com.hazelcast.jet.Jet
import com.hazelcast.jet.JetInstance
import com.hazelcast.jet.Job
import com.hazelcast.jet.config.JetConfig
import com.hazelcast.jet.core.JobStatus
import com.hazelcast.map.IMap
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory

object ClusterUtils {
    val log = LoggerFactory.getLogger(ClusterUtils::class.java)
    var jetNode: JetInstance? = null
    var cacheNode: HazelcastInstance? = null

    fun getJetConfig(): JetConfig {
        val config = JetConfig()
        config.hazelcastConfig = YamlConfigBuilder(ClusterUtils::class.java.classLoader.getResourceAsStream("jet.yaml")).build()
        return config
    }

    fun setupJet(): JetInstance {
        return Jet.newJetInstance(getJetConfig());
    }

    fun getJetClient(): JetInstance {
        if(jetNode == null) {
            val clientConfig: ClientConfig = YamlClientConfigBuilder(ClusterUtils::class.java.classLoader.getResourceAsStream("jet-client.yaml")).build()
            jetNode = Jet.newJetClient(clientConfig)
        }
        return jetNode!!
    }

    fun getCacheConfig(): JetConfig {
        val config = JetConfig()
        config.hazelcastConfig = YamlConfigBuilder(ClusterUtils::class.java.classLoader.getResourceAsStream("cache.yaml")).build()
        return config
    }

    fun setupCache(): HazelcastInstance {
        return Jet.newJetInstance(getCacheConfig()).hazelcastInstance
    }

    fun getCacheClientConfig() : ClientConfig {
        return  YamlClientConfigBuilder(ClusterUtils::class.java.classLoader.getResourceAsStream("cache-client.yaml")).build()
    }

    fun getCacheClient(): HazelcastInstance {
        if(cacheNode == null) {
            cacheNode = HazelcastClient.newHazelcastClient(this.getCacheClientConfig())
        }
        return cacheNode!!
    }

    fun <T>getCacheMap(dataMap: DataMap): IMap<String, T> {
        val cacheClient = getCacheClient()
        return cacheClient.getMap(dataMap.mapName);
    }

    fun cancelJob(job: Job?) {
        if(job != null) {
            log.info("[${job.name}] cancelling job, status: ${job.status}")
            runBlocking {
                while(job.status == JobStatus.RUNNING) {
                    log.info("[${job.name}] job exists, status: ${job.status}, cancelling...")
                    job.cancel()
                    delay(500)
                }
            }
            log.info("[${job.name}] cancelled")

        }
    }
}
