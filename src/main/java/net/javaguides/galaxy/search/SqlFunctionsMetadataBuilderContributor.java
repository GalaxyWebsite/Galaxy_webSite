package net.javaguides.galaxy.search;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.BooleanType;

public class SqlFunctionsMetadataBuilderContributor implements MetadataBuilderContributor {
    @Override
    public void contribute(MetadataBuilder metadataBuilder) {
        metadataBuilder.applySqlFunction("search",
                new SQLFunctionTemplate(BooleanType.INSTANCE,
                        "to_tsvector(topic) @@ plainto_tsquery(?1)"));
    }
}

