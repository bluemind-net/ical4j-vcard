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
package net.fortuna.ical4j.vcard;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.fortuna.ical4j.vcard.Parameter.Id;
import net.fortuna.ical4j.vcard.parameter.Encoding;
import net.fortuna.ical4j.vcard.parameter.Language;
import net.fortuna.ical4j.vcard.parameter.Pid;
import net.fortuna.ical4j.vcard.parameter.Pref;
import net.fortuna.ical4j.vcard.parameter.Type;
import net.fortuna.ical4j.vcard.parameter.Value;

/**
 * $Id$
 *
 * Created on: 05/01/2009
 *
 * @author Ben
 *
 */
public class ParameterFactoryRegistry {

    private static final Log LOG = LogFactory.getLog(ParameterFactoryRegistry.class);
    
    private Map<Id, ParameterFactory<? extends Parameter>> defaultFactories;

    private Map<String, ParameterFactory<? extends Parameter>> extendedFactories;
    
    public ParameterFactoryRegistry() {
        defaultFactories = new HashMap<Id, ParameterFactory<? extends Parameter>>();
        defaultFactories.put(Parameter.Id.ENCODING, new ParameterFactory<Encoding>() {
            @Override
            public Encoding createParameter(final String value) {
                if (Encoding.B.getValue().equals(value)) {
                    return Encoding.B;
                }
                return new Encoding(value);
            }
        });
        defaultFactories.put(Parameter.Id.LANGUAGE, new ParameterFactory<Language>() {
            @Override
            public Language createParameter(final String value) {
                return new Language(new Locale(value));
            }
        });
        defaultFactories.put(Parameter.Id.PID, new ParameterFactory<Pid>() {
            @Override
            public Pid createParameter(final String value) {
                return new Pid(Integer.valueOf(value));
            }
        });
        defaultFactories.put(Parameter.Id.TYPE, new ParameterFactory<Type>() {
            @Override
            public Type createParameter(final String value) {
                if (Type.HOME.getValue().equals(value)) {
                    return Type.HOME;
                }
                else if (Type.PREF.getValue().equals(value)) {
                    return Type.PREF;
                }
                else if (Type.WORK.getValue().equals(value)) {
                    return Type.WORK;
                }
                return new Type(value);
            }
        });
        defaultFactories.put(Parameter.Id.VALUE, new ParameterFactory<Value>() {
            @Override
            public Value createParameter(final String value) {
                if (Value.BINARY.getValue().equals(value)) {
                    return Value.BINARY;
                }
                else if (Value.TEXT.getValue().equals(value)) {
                    return Value.TEXT;
                }
                else if (Value.URI.getValue().equals(value)) {
                    return Value.URI;
                }
                return new Value(value);
            }
        });
        defaultFactories.put(Parameter.Id.PREF, new ParameterFactory<Pref>() {
            /* (non-Javadoc)
             * @see net.fortuna.ical4j.vcard.ParameterFactory#createParameter(java.lang.String)
             */
            @Override
            public Pref createParameter(final String value) {
                return Pref.PREF;
            }
        });
        
        extendedFactories = new HashMap<String, ParameterFactory<? extends Parameter>>();
    }
    
    /**
     * @param value
     * @return
     */
    public ParameterFactory<? extends Parameter> getFactory(final String value) {
        try {
            return defaultFactories.get(Id.valueOf(value));
        }
        catch (Exception e) {
            LOG.info("Not a default parameter: [" + value + "]");
        }
        return extendedFactories.get(value);
    }
    
    /**
     * @param extendedName
     * @param factory
     */
    public void register(String extendedName, ParameterFactory<Parameter> factory) {
        extendedFactories.put(extendedName, factory);
    }
}