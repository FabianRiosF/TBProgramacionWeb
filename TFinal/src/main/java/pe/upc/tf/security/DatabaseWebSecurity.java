package pe.upc.tf.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	/**
	 *  Implementación de Spring Security que encripta passwords con el algoritmo Bcrypt
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
	   return new BCryptPasswordEncoder();
    }
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username, password, estatus from Usuarios where username=?")
		.authoritiesByUsernameQuery("select username, IF(estatus = 1, 'ADMINISTRADOR', 'USUARIO') from Usuarios where username = ?");
	}

	/**
	 * Personalizamos el Acceso a las URLs de la aplicación
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()             	
    	// Los recursos estáticos no requieren autenticación
        .antMatchers(
                "/bootstrap/**",                        
                "/images/**",
                "/tinymce/**",
                "/vendor/**").permitAll()        
        // Las vistas públicas u otras específicas no requieren autenticación
        .antMatchers( "/").permitAll()        
        // Permisos de URLs por ROLES
        //.antMatchers("/**").hasAnyAuthority("ADMINISTRADOR")
        .antMatchers("/actor/**").permitAll()
        .antMatchers("/add-user/**").hasAnyAuthority("ADMINISTRADOR")
        .antMatchers("/administrador/**").hasAnyAuthority("ADMINISTRADOR")
        .antMatchers("/forgot-password/**").hasAnyAuthority("USUARIO")
        .antMatchers("/ListaUsuario/**").hasAnyAuthority("USUARIO")
        .antMatchers("/ModificarUsuario/**").hasAnyAuthority("USUARIO")
        .antMatchers("/pelicula_serie/**").permitAll()
        .antMatchers("/update-user/**").hasAnyAuthority("ADMINISTRADOR")
        .antMatchers("/update-usuario/**").hasAnyAuthority("USUARIO")
        .antMatchers("/register/**").permitAll()
        
        // El resto de URLs de la Aplicación requieren autenticación
        .anyRequest().permitAll()
        // El formulario de Login no requiere autenticacion
        .and().formLogin().loginPage("/login").permitAll()        
        .and().logout().permitAll().and()
		.exceptionHandling().accessDeniedPage("/error");;
    }
	
}