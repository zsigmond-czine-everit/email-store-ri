package org.everit.email.store.ri.schema.qdsl;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;




/**
 * QAddress is a Querydsl query type for QAddress
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QAddress extends com.mysema.query.sql.RelationalPathBase<QAddress> {

    private static final long serialVersionUID = 1643506677;

    public static final QAddress address1 = new QAddress("EMSTR_ADDRESS");

    public class PrimaryKeys {

        public final com.mysema.query.sql.PrimaryKey<QAddress> emstrAddressPk = createPrimaryKey(emailAddressId);

    }

    public class ForeignKeys {

        public final com.mysema.query.sql.ForeignKey<QEmailRecipient> _emstrEmailRcpntAddressFk = createInvForeignKey(emailAddressId, "EMAIL_ADDRESS_ID");

    }

    public final StringPath address = createString("address");

    public final NumberPath<Long> emailAddressId = createNumber("emailAddressId", Long.class);

    public final StringPath personal = createString("personal");

    public final NumberPath<Long> storedEmailId = createNumber("storedEmailId", Long.class);

    public final PrimaryKeys pk = new PrimaryKeys();

    public final ForeignKeys fk = new ForeignKeys();

    public QAddress(String variable) {
        super(QAddress.class, forVariable(variable), "org.everit.email.store.ri", "EMSTR_ADDRESS");
        addMetadata();
    }

    public QAddress(String variable, String schema, String table) {
        super(QAddress.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAddress(Path<? extends QAddress> path) {
        super(path.getType(), path.getMetadata(), "org.everit.email.store.ri", "EMSTR_ADDRESS");
        addMetadata();
    }

    public QAddress(PathMetadata<?> metadata) {
        super(QAddress.class, metadata, "org.everit.email.store.ri", "EMSTR_ADDRESS");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(address, ColumnMetadata.named("ADDRESS").ofType(12).withSize(255).notNull());
        addMetadata(emailAddressId, ColumnMetadata.named("EMAIL_ADDRESS_ID").ofType(-5).withSize(19).notNull());
        addMetadata(personal, ColumnMetadata.named("PERSONAL").ofType(12).withSize(255).notNull());
        addMetadata(storedEmailId, ColumnMetadata.named("STORED_EMAIL_ID").ofType(-5).withSize(19).notNull());
    }

}

