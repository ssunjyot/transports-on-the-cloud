package com.bonial.challenge.service;

import com.bonial.challenge.model.TransportName;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class TransportFactory {

  //NOTE: these are sorted keys for each type of transport JSON delimited with ;
  static final String delimitedCarKeysSorted = "manufacturer;model;passenger-capacity";
  static final String delimitedTrainKeysSorted = "model;number-wagons;w-passenger-capacity";
  static final String delimitedPlaneKeysSorted = "b-passenger-capacity;e-passenger-capacity;model";

  public TransportCalculatorService getTransportType(JSONObject transportJSONRecord, Logger log, Map<String, TransportCalculatorService> transportServicesMap){
    Set<String> JSONKeys = new TreeSet<String>(transportJSONRecord.keySet());
    String JSONKeysString = String.join(";",JSONKeys);

    log.info("Keys String" + JSONKeysString);

    switch (JSONKeysString){
      case delimitedCarKeysSorted:
        return transportServicesMap.get(TransportName.CAR.name());

      case delimitedTrainKeysSorted:
        return transportServicesMap.get(TransportName.TRAIN.name());

      case delimitedPlaneKeysSorted:
        return transportServicesMap.get(TransportName.PLANE.name());

      default:
        throw new InputMismatchException("Illegal keys present in the transport JSON records");

    }
  }

}
