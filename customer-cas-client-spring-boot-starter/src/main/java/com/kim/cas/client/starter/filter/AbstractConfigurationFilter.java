package com.kim.cas.client.starter.filter;

import com.kim.cas.client.starter.config.CasClientConfigurationProperties;
import com.kim.cas.client.starter.config.CasClientConfigurer;
import net.unicon.cas.client.configuration.EnableCasClient;
import org.jasig.cas.client.Protocol;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.jasig.cas.client.validation.Saml11TicketValidator;
import org.jasig.cas.client.validation.TicketValidator;

import javax.servlet.Filter;

/**
 * @author huangjie
 * @description
 * @date 2020/5/21
 */
public abstract class AbstractConfigurationFilter implements Filter {

    protected CasClientConfigurer casClientConfigurer;
    protected CasClientConfigurationProperties properties;



    public AbstractConfigurationFilter(CasClientConfigurer casClientConfigurer,CasClientConfigurationProperties properties){
        this.casClientConfigurer=casClientConfigurer;
        this.properties=properties;
    }

    protected Protocol getProtocol(){
        EnableCasClient.ValidationType validationType = properties.getValidationType();
        return validationType != EnableCasClient.ValidationType.CAS && validationType != EnableCasClient.ValidationType.CAS3 ?  Protocol.SAML11: Protocol.CAS2;

    }

    protected TicketValidator getTicketValidator(){
        TicketValidator ticketValidator=null;
        switch(properties.getValidationType()) {
            case CAS:
                ticketValidator = new Cas20ServiceTicketValidator(properties.getServerUrlPrefix());
                break;
            case CAS3:
                ticketValidator = new Cas30ServiceTicketValidator(properties.getServerUrlPrefix());
                break;
            case SAML:
                ticketValidator = new Saml11TicketValidator(properties.getServerUrlPrefix());
                break;
            default:
                throw new IllegalStateException("Unknown CAS validation type");
        }
        return ticketValidator;
    }




}
