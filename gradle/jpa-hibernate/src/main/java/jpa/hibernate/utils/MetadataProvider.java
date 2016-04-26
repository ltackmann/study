package jpa.hibernate.utils;

import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.boot.spi.SessionFactoryBuilderFactory;
import org.hibernate.boot.spi.SessionFactoryBuilderImplementor;

public class MetadataProvider implements SessionFactoryBuilderFactory {
    private static MetadataImplementor metadata;

    @Override
    public SessionFactoryBuilder getSessionFactoryBuilder(MetadataImplementor metadata, SessionFactoryBuilderImplementor defaultBuilder) {
        this.metadata = metadata;
        return defaultBuilder; //Just return the one provided in the argument itself. All we care about is the metadata :)
    }

    public static MetadataImplementor getMetadata() {
        return metadata;
    }
}
