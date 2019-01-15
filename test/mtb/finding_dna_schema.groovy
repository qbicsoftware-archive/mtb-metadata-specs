// https://mvnrepository.com/artifact/org.everit.json/org.everit.json.schema
@GrabResolver(name='jitpack.io', root='https://jitpack.io')
@Grapes(
    @Grab(group='com.github.everit-org.json-schema', module='org.everit.json.schema', version='1.10.0')
)

import org.everit.json.schema.ValidationException
import org.everit.json.schema.Schema
import org.everit.json.schema.loader.SchemaLoader
import org.json.JSONObject
import org.json.JSONTokener

def projectRootDir = args ? args[0] : "."


InputStream inputStream = new FileInputStream("${projectRootDir}/schemes/mtb/finding.dna.schema.json")
//InputStream inputStream = new FileInputStream("${projectRootDir}/schemes/mtb/definitions/clinical.common.json")
JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
File example = new File("${projectRootDir}/test/mtb/finding.dna.example.json")
Schema schema = SchemaLoader.load(rawSchema)
try {
  schema.validate(new JSONObject(new JSONTokener(example.newDataInputStream()))) // throws a ValidationException if this object is invalid
} catch (ValidationException e) {
    System.out.println(e.getMessage())
    e.getCausingExceptions().each {
        print "${it.getAllMessages()}\n"
    }
}

