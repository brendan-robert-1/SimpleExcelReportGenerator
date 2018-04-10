/**
 *
 */
package net.evolveip.report.utilities;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author brobert
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RecordElement {

	public String name() default "Field";



	public int index() default 0;



	/**
	 * @return
	 */
	boolean include() default true;
}
