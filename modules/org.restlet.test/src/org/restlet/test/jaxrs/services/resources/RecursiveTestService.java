/*
 * Copyright 2005-2008 Noelios Consulting.
 * 
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the "License"). You may not use this file except in
 * compliance with the License.
 * 
 * You can obtain a copy of the license at
 * http://www.opensource.org/licenses/cddl1.txt See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing Covered Code, include this CDDL HEADER in each file and
 * include the License file at http://www.opensource.org/licenses/cddl1.txt If
 * applicable, add the following below this CDDL HEADER, with the fields
 * enclosed by brackets "[]" replaced with your own identifying information:
 * Portions Copyright [yyyy] [name of copyright owner]
 */
package org.restlet.test.jaxrs.services.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.restlet.test.jaxrs.services.tests.RecursiveTest;

/**
 * Test, if there are problems if a resource is a child of itself.
 * 
 * @author Stephan Koops
 * @see RecursiveTest
 */
@Path("/recursiveTest")
@Produces("text/plain")
public class RecursiveTestService {

    /**
     * use from runtime only
     */
    @Deprecated
    public RecursiveTestService() {
    }

    public RecursiveTestService(RecursiveTestService parent) {
        this.parent = parent;
    }

    private RecursiveTestService parent;

    @GET
    public String get() {
        return String.valueOf(depth());
    }

    @Path("a")
    public RecursiveTestService getSubResource2() {
        return new RecursiveTestService(this);
    }

    public int depth() {
        if (parent == null)
            return 0;
        return parent.depth() + 1;
    }
}