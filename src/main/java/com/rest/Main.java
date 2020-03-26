package com.rest;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

@Controller
@SpringBootApplication
public class Main {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
  }

  @RequestMapping("/")
  String index() {
    return "index";
  }

  @RequestMapping(value="/update", method= RequestMethod.GET)
  @ResponseBody
  public String update() throws Exception {
    RetrieveStoreData pullUpdate = new RetrieveStoreData();
    pullUpdate.RetrieveStoreData();
    DbInterface testItem = new DbInterface();
    String returnJson = testItem.retrieveLatest("single");
    return returnJson;
  }

  @RequestMapping(value="/updates", method= RequestMethod.GET)
  @ResponseBody
  public String updates() throws Exception {
    RetrieveStoreData pullUpdate = new RetrieveStoreData();
    pullUpdate.RetrieveStoreData();
    DbInterface testItem = new DbInterface();
    String returnJson = testItem.retrieveLatest("all");
    return returnJson;
  }
}
