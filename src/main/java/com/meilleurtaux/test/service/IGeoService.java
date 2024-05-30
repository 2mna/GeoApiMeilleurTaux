package com.meilleurtaux.test.service;


import com.meilleurtaux.test.record.Town;

import java.util.List;

public interface IGeoService {

    List<Town> getTownsByPostalCode(String postalCode);
}
