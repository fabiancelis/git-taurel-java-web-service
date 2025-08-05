package resources.properties;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class ApplicationProperties extends PropertyPlaceholderConfigurer {

	/**
	 * <code>Map</code> que contiene las propiedades definidas para la
	 * aplicaci&oacute;n.
	 */
	private static Map<String, String> properties;

	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactory, Properties props)
			throws BeansException {
		super.processProperties(beanFactory, props);

		properties = new HashMap<String, String>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			properties.put(keyStr, props.getProperty(keyStr));
		}
	}

	// /*
	// * (non-Javadoc)
	// */
	// @Override
	// protected void loadProperties(final Properties props) throws IOException
	// {
	// super.loadProperties(props);
	// for (final Object key : props.keySet()) {
	// properties.put((String) key, props.getProperty((String) key));
	// }
	// }

	/**
	 * Retorna el valor de una propiedad, dado su nombre.
	 * 
	 * @param name
	 *            <code>String</code> nombre de la propieda
	 * @return <code>String</code> con el valor de la propiedad.
	 */
	public static String getProperty(final String name) {
		return properties.get(name);
	}
}