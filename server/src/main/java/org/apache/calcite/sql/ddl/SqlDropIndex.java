package org.apache.calcite.sql.ddl;

import org.apache.calcite.jdbc.CalcitePrepare;
import org.apache.calcite.jdbc.CalciteSchema;
import org.apache.calcite.sql.SqlIdentifier;
import org.apache.calcite.sql.SqlKind;
import org.apache.calcite.sql.SqlOperator;
import org.apache.calcite.sql.SqlSpecialOperator;
import org.apache.calcite.sql.parser.SqlParserPos;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class SqlDropIndex extends SqlDropObject {
    private static final SqlOperator OPERATOR =
            new SqlSpecialOperator("DROP INDEX", SqlKind.DROP_INDEX);

    //protected final boolean ifExists; // 在SqlDrop中已定义
    //protected final SqlIdentifier name; // 在SqlDropObject中已定义
    protected final SqlIdentifier table;

    SqlDropIndex(SqlParserPos pos,
                 boolean ifExists, SqlIdentifier id, SqlIdentifier table) {
        super(OPERATOR, pos, ifExists, id);
        this.table = table;
    }

    /**
     * 被executeDdl调用，需要完成对Schema的修改和实际保存的索引表的删除
     */
    @Override
    public void execute(CalcitePrepare.Context context) {
        final List<String> path = context.getDefaultSchemaPath();
        CalciteSchema schema = context.getRootSchema();
        for (String p : path) {
            schema = schema.getSubSchema(p, true);
        }
        final boolean existed;
        switch (getKind()) {
            case DROP_INDEX:
                throw new NotImplementedException();
                //break;
            default:
                throw new AssertionError(getKind());
        }
    }
}
