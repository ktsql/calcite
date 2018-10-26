package org.apache.calcite.sql.ddl;

import com.google.common.collect.ImmutableList;
import org.apache.calcite.adapter.java.JavaTypeFactory;
import org.apache.calcite.jdbc.CalcitePrepare;
import org.apache.calcite.jdbc.CalciteSchema;
import org.apache.calcite.jdbc.JavaTypeFactoryImpl;
import org.apache.calcite.linq4j.Ord;
import org.apache.calcite.plan.RelOptTable;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.rel.type.RelDataTypeField;
import org.apache.calcite.rel.type.RelDataTypeImpl;
import org.apache.calcite.rex.RexNode;
import org.apache.calcite.schema.ColumnStrategy;
import org.apache.calcite.schema.TranslatableTable;
import org.apache.calcite.schema.impl.ViewTable;
import org.apache.calcite.schema.impl.ViewTableMacro;
import org.apache.calcite.sql.*;
import org.apache.calcite.sql.dialect.CalciteSqlDialect;
import org.apache.calcite.sql.parser.SqlParserPos;
import org.apache.calcite.sql2rel.InitializerContext;
import org.apache.calcite.sql2rel.InitializerExpressionFactory;
import org.apache.calcite.sql2rel.NullInitializerExpressionFactory;
import org.apache.calcite.util.ImmutableNullableList;
import org.apache.calcite.util.Pair;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

import static org.apache.calcite.util.Static.RESOURCE;

/**
 * SqlCreateIndex参照calcite-server ddl的扩展模式，由executeDdl()调用，
 * 实现SqlExecutableStatement接口
 */
public class SqlCreateIndex extends SqlCreate implements SqlExecutableStatement {
    private static final SqlOperator OPERATOR =
            new SqlSpecialOperator("CREATE INDEX", SqlKind.CREATE_INDEX);

    protected final SqlIdentifier indexName;
    protected final SqlIdentifier indexType;
    protected final SqlIdentifier table;
    protected final SqlNodeList indexKeys;

    SqlCreateIndex(SqlParserPos pos, boolean replace,
                   boolean ifNotExists,
                   SqlIdentifier id,
                   SqlIdentifier indexType,
                   SqlIdentifier table,
                   SqlNodeList indexElementList) {
        super(OPERATOR, pos, replace, ifNotExists);
        this.indexName = id;
        this.indexType = indexType;
        this.table = table;
        this.indexKeys = indexElementList;
    }

    @Override
    public List<SqlNode> getOperandList() {
        return ImmutableNullableList.of(indexName, indexType, indexKeys);
    }

    /**
     * 创建索引的时候，首先需要对所有的数据建立索引表，然后在元数据表中插入索引信息。
     * @param context
     */
    @Override
    public void execute(CalcitePrepare.Context context) {
        //throw new NotImplementedException(); // add index to schema and storage
    }
}
