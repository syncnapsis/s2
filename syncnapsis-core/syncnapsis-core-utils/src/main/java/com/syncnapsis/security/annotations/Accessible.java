/**
 * Syncnapsis Framework - Copyright (c) 2012-2014 ultimate
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of
 * the GNU General Public License as published by the Free Software Foundation; either version
 * 3 of the License, or any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MECHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Plublic License along with this program;
 * if not, see <http://www.gnu.org/licenses/>.
 */
package com.syncnapsis.security.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.syncnapsis.security.AccessRule;

/**
 * Annotation indicating the accessibility of a Field or Method.<br>
 * Used for (De)-Serialization or Method Invocation there are different ways to annotate Elements:<br>
 * <br>
 * (De)-Serialization:<br>
 * <ul>
 * <li>Annotation on Getter --> Accessible defines Authorities for READ</li>
 * <li>Annotation on Setter --> Accessible defines Authorities for WRITE</li>
 * <li>Annotation on Field --> Accessible defines Authorities for READ AND WRITE</li>
 * <li>(If Getter is annotated Field will be ignored for Serialization)</li>
 * <li>(If Setter is annotated Field will be ignored for Deserialization)</li>
 * </ul>
 * <br>
 * Method Invocation:<br>
 * <ul>
 * <li>Annotation on Method --> General Accessibility defined by Annotation</li>
 * </ul>
 * 
 * @author ultimate
 */
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Accessible
{
	int by() default AccessRule.NOBODY;
	int of() default AccessRule.ANYROLE;
}
