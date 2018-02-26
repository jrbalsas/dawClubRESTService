/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.club;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author jrbalsas
 */
@ApplicationPath("webservice") //  Service URL: /webservice/*
public class ClubRESTService extends ResourceConfig {

    public ClubRESTService() {
        super();
        //Configure JAX-RS implementation for sending BeanValidation messages
        property("jersey.config.beanValidation.enableOutputValidationErrorEntity.server", true);
    }
    
}
