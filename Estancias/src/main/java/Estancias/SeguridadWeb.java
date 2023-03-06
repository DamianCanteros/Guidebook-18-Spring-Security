
package Estancias;

import estancias.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Damian
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SeguridadWeb extends WebSecurityConfigurerAdapter{

    @Autowired
    public UsuarioServicio us;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        
        auth.userDetailsService(us).passwordEncoder(new BCryptPasswordEncoder());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {//restringe el acceso por roles
        
        http.authorizeRequests()
                .antMatchers("/css/*","/js/*","/img/*","/**")
                .permitAll()
            .and().formLogin()
                .loginPage("/usuario/login")
                .loginProcessingUrl("/logincheck")
                .usernameParameter("alias")
                .passwordParameter("clave")
                .defaultSuccessUrl("/usuario/inicio")
                .permitAll()
            .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/estancia/inicio")
                .permitAll()
            .and().csrf()
                .disable();
    }
}
