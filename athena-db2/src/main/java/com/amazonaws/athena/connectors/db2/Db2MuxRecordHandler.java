/*-
 * #%L
 * athena-db2
 * %%
 * Copyright (C) 2019 - 2022 Amazon Web Services
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.amazonaws.athena.connectors.db2;

import com.amazonaws.athena.connectors.jdbc.MultiplexingJdbcRecordHandler;
import com.amazonaws.athena.connectors.jdbc.connection.DatabaseConnectionConfig;
import com.amazonaws.athena.connectors.jdbc.connection.JdbcConnectionFactory;
import com.amazonaws.athena.connectors.jdbc.manager.JdbcRecordHandler;
import com.amazonaws.athena.connectors.jdbc.manager.JdbcRecordHandlerFactory;
import com.amazonaws.services.athena.AmazonAthena;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.google.common.annotations.VisibleForTesting;

import java.util.Map;

class Db2MuxRecordHandlerFactory implements JdbcRecordHandlerFactory
{
    @Override
    public String getEngine()
    {
        return Db2Constants.NAME;
    }

    @Override
    public JdbcRecordHandler createJdbcRecordHandler(DatabaseConnectionConfig config)
    {
        return new Db2RecordHandler(config);
    }
}

public class Db2MuxRecordHandler extends MultiplexingJdbcRecordHandler
{
    public Db2MuxRecordHandler()
    {
        super(new Db2MuxRecordHandlerFactory());
    }

    @VisibleForTesting
    Db2MuxRecordHandler(final AmazonS3 amazonS3, final AWSSecretsManager secretsManager, final AmazonAthena athena, final JdbcConnectionFactory jdbcConnectionFactory,
                               final DatabaseConnectionConfig databaseConnectionConfig, final Map<String, JdbcRecordHandler> recordHandlerMap)
    {
        super(amazonS3, secretsManager, athena, jdbcConnectionFactory, databaseConnectionConfig, recordHandlerMap);
    }
}
