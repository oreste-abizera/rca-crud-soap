package rca.soap.api.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//Enable Spring Web Services
@EnableWs
//Spring Configuration
@Configuration
public class WebServiceConfig{

	// MessageDispatcherServlet
		// ApplicationContext
		// url -> /ws/*

		@Bean
		public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
			MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
			messageDispatcherServlet.setApplicationContext(context);
			messageDispatcherServlet.setTransformWsdlLocations(true);
			return new ServletRegistrationBean(messageDispatcherServlet, "/ws/oreste/*");
		}


	// /ws/oreste/suppliers.wsdl
	// supplier-details.xsd
	@Bean(name = "courses")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema coursesSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setPortTypeName("SupplierPort");
		definition.setTargetNamespace("http://soap.rca/oreste/suppliers");
		definition.setLocationUri("/ws/oreste");
		definition.setSchema(coursesSchema);
		return definition;
	}

	@Bean
	public XsdSchema suppliersSchema() {
		return new SimpleXsdSchema(new ClassPathResource("supplier-details.xsd"));
	}
}
