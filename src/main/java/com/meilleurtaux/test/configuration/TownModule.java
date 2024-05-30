package com.meilleurtaux.test.configuration;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.meilleurtaux.test.record.Town;

public class TownModule extends SimpleModule {
    public TownModule() {
        super("TownModule", Version.unknownVersion());
        addDeserializer(Town.class, new TownDeserializer());
    }

}
