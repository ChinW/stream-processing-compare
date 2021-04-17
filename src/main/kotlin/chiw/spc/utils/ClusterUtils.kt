package chiw.spc.utils

import com.hazelcast.client.config.ClientConfig
import com.hazelcast.client.config.YamlClientConfigBuilder
import com.hazelcast.config.YamlConfigBuilder
import com.hazelcast.jet.Jet
import com.hazelcast.jet.JetInstance
import com.hazelcast.jet.config.JetConfig
import org.slf4j.LoggerFactory

object ClusterUtils {
    val log = LoggerFactory.getLogger(ClusterUtils::class.java)
    var jetNode: JetInstance? = null

    fun getJetConfig(): JetConfig {
        val config = JetConfig()
        config.hazelcastConfig = YamlConfigBuilder(ClusterUtils::class.java.classLoader.getResourceAsStream("jet-cache.yaml")).build()
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
}
