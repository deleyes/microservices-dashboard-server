/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.ordina.msdashboard.nodes.uriresolvers;

import com.netflix.appinfo.InstanceInfo;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.cloud.netflix.eureka.InstanceInfoFactory;

/**
 * Resolves urls from a {@link ServiceInstance} using Eureka's
 * {@link org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient.EurekaServiceInstance}
 * abstraction.
 * @author Andreas Evers
 */
public class EurekaUriResolver implements UriResolver {

    @Override
    public String resolveHomePageUrl(ServiceInstance instance) {
        return ((EurekaDiscoveryClient.EurekaServiceInstance) instance).getInstanceInfo().getHomePageUrl();
    }

    @Override
    public String resolveHealthCheckUrl(ServiceInstance instance) {
        return ((EurekaDiscoveryClient.EurekaServiceInstance) instance).getInstanceInfo().getHealthCheckUrl();
    }

    @Override
    public String resolveMappingsUrl(ServiceInstance instance) {
        return resolveHealthCheckUrl(instance).replaceFirst("health", "mappings");
    }

    @Override
    public String resolveInfoUrl(ServiceInstance instance) {
        InstanceInfo instanceInfo = ((EurekaDiscoveryClient.EurekaServiceInstance) instance).getInstanceInfo();

        return instanceInfo.getHostName() + ":" + instanceInfo.getPort() + "/actuator/info";
    }
}
