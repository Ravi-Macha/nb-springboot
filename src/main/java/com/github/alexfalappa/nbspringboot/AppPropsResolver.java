/*
 * Copyright 2016 Alessandro Falappa.
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
package com.github.alexfalappa.nbspringboot;

import java.util.regex.Pattern;

import org.openide.filesystems.FileObject;
import org.openide.filesystems.MIMEResolver;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Alessandro Falappa
 */
@ServiceProvider(service = MIMEResolver.class, position = 10)
public class AppPropsResolver extends MIMEResolver {

    public static final String MIME_APPPROPS = "text/application+properties";
    private static final Pattern BOOT_APP_PROPS = Pattern.compile("application(-\\w+)?\\.properties");

    public AppPropsResolver() {
        super(new String[]{MIME_APPPROPS});
    }

    @Override
    public String findMIMEType(FileObject fo) {
        System.out.println("Finding mime type for " + fo.getNameExt());
        if (fo.getExt().equals("props") || BOOT_APP_PROPS.matcher(fo.getNameExt()).matches()) {
            return MIME_APPPROPS;
        }
        return null;
    }
}
