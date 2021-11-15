package micdoodle8.mods.galacticraft.annotations;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Functionality annotated with ReplaceWith should be replaced immediately
 * with the mentioned code fragment. This is often used for things like naming changes.
 */
@Retention(RUNTIME)
@Target(
{TYPE, FIELD, METHOD, CONSTRUCTOR})
public @interface ReplaceWith
{
    String value();
}
