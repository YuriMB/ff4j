package org.ff4j.web.resources.it;

/*
 * #%L
 * ff4j-web
 * %%
 * Copyright (C) 2013 - 2014 Ff4J
 * %%
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
 * #L%
 */

import javax.ws.rs.core.Response.Status;

import junit.framework.Assert;

import org.ff4j.core.Feature;
import org.junit.Test;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Externalisation of PUT REQUEST.
 *
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class FeatureResource_putUpdateAuth2_TestIT extends AbstractWebResourceTestIT {

    /**
     * TDD, update by adding in the authorization
     */
    @Test
    public void testPut_upsertUpdateRemoveAuthorization() {
        // Given
        assertFF4J.assertThatFeatureExist(F1);
        assertFF4J.assertThatFeatureHasRole(F1, ROLE_USER);
        // When
        Feature fNew = ff4j.getFeature(F1);
        fNew.getPermissions().remove(ROLE_USER);
        WebResource webResFeat = resourceFeatures().path(F1);
        ClientResponse res = webResFeat.put(ClientResponse.class, fNew.toString().getBytes());
        // Then HTTPResponse
        Assert.assertEquals(Status.NO_CONTENT.getStatusCode(), res.getStatus());
        // Then Object Entity : null
        // Then
        assertFF4J.assertThatFeatureHasNotRole(F1, ROLE_USER);
    }


}
