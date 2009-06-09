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
import java.util.List;
import java.util.Locale;

import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.vcard.Parameter;
import net.fortuna.ical4j.vcard.Property;

/**
 * $Id$
 *
 * Created on 24/08/2008
 *
 * @author Ben
 */
public final class Language extends Property {

    /**
     * 
     */
    private static final long serialVersionUID = 1863658302945551760L;

    private Locale[] locales;

    /**
     * @param locales one or more locales that define the language instance
     */
    public Language(Locale... locales) {
        super(Id.LANG);
        if (locales.length == 0) {
            throw new IllegalArgumentException("Must have at least one locale");
        }
        this.locales = locales;
    }

    /**
     * Factory constructor.
     * @param params property parameters
     * @param value string representation of a property value
     */
    public Language(List<Parameter> params, String value) {
        super(Id.LANG, params);
        List<Locale> list = new ArrayList<Locale>();
        for (String langString : value.split(",")) {
            list.add(new Locale(langString));
        }
        this.locales = list.toArray(new Locale[list.size()]);
    }

    /**
     * @return the locales
     */
    public Locale[] getLocales() {
        return locales;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getValue() {
        final StringBuilder b = new StringBuilder();
        for (int i = 0; i < locales.length; i++) {
            if (i > 0) {
                b.append(',');
            }
            b.append(locales[i].getLanguage());
        }
        return b.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }

}
