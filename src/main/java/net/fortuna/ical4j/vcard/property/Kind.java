/**
 * Copyright (c) 2009, Ben Fortuna
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  o Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 *  o Neither the name of Ben Fortuna nor the names of any other contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.fortuna.ical4j.vcard.property;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.vcard.Parameter;
import net.fortuna.ical4j.vcard.Property;

/**
 * $Id$
 *
 * Created on 22/08/2008
 *
 * @author Ben
 *
 */
public final class Kind extends Property {

    /**
     * 
     */
    private static final long serialVersionUID = -3114221975393833838L;
    
    public static final Kind INDIVIDUAL = new Kind("individual", Collections.unmodifiableList(new ArrayList<Parameter>()));
    
    public static final Kind GROUP = new Kind("group", Collections.unmodifiableList(new ArrayList<Parameter>()));
    
    public static final Kind ORG = new Kind("org", Collections.unmodifiableList(new ArrayList<Parameter>()));
    
    private String value;
    
    /**
     * @param value
     */
    public Kind(String value) {
        super(Id.KIND);
        this.value = value;
    }
    
    /**
     * @param value
     * @param parameters
     */
    private Kind(String value, List<Parameter> parameters) {
        super(Id.KIND, parameters);
        this.value = value;
    }
    
    /* (non-Javadoc)
     * @see net.fortuna.ical4j.vcard.Property#getValue()
     */
    @Override
    public String getValue() {
        return value;
    }

    /* (non-Javadoc)
     * @see net.fortuna.ical4j.vcard.Property#validate()
     */
    @Override
    public void validate() throws ValidationException {
        // ; No parameters allowed
        assertParametersEmpty();
    }

}
