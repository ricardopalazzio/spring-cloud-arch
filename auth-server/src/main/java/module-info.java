open module api {

    requires spring.core;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.data.jpa;
    requires spring.tx;
    requires spring.data.commons;
    requires spring.webmvc;
    requires spring.security.config;
    requires spring.security.core;
    requires jackson.annotations;
    requires tomcat.embed.core;
    requires spring.web;
    requires slf4j.api;
    requires spring.beans;
    requires spring.security.web;
    requires org.hibernate.orm.envers;
    requires spring.context;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.hibernate5;
    requires org.hibernate.orm.core;
    requires java.validation;
    requires jjwt;
    requires  java.base;
    requires java.persistence;
    requires java.sql;
    requires java.transaction;
    requires java.xml.bind;

}