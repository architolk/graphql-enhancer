package nl.architolk.graphql.enhancer;

import java.io.File;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.SchemaPrinter;
import graphql.language.TypeDefinition;

public class Enhancer {

  private static final Logger LOG = LoggerFactory.getLogger(Enhancer.class);

  private static RuntimeWiring buildRuntimeWiring() {
          return RuntimeWiring.newRuntimeWiring().build();
  }

  public static void main(String[] args) {

      LOG.info("Starting, with file: {}",args[0]);

    SchemaParser schemaParser = new SchemaParser();
    SchemaGenerator schemaGenerator = new SchemaGenerator();

    File schemaFile1 = new File(args[0]);

    TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();

    // each registry is merged into the main registry
    typeRegistry.merge(schemaParser.parse(schemaFile1));

    for (Map.Entry<String,TypeDefinition> entry : typeRegistry.types().entrySet()) {
      String typeName = entry.getKey();
      TypeDefinition typeDef = entry.getValue();
      LOG.info("Typename: {}", typeName);
      LOG.info("INFO: {}", typeDef);
    }

    //GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, buildRuntimeWiring());
    //SchemaPrinter printer = new SchemaPrinter();
    //LOG.info(printer.print(graphQLSchema));

  }
}
