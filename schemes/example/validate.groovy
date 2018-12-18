// https://mvnrepository.com/artifact/org.everit.json/org.everit.json.schema
@GrabResolver(name='jitpack.io', root='https://jitpack.io')
@Grapes(
    @Grab(group='com.github.everit-org.json-schema', module='org.everit.json.schema', version='1.10.0')
)

import org.everit.json.schema.Schema
import org.everit.json.schema.loader.SchemaLoader
import org.json.JSONObject
import org.json.JSONTokener


InputStream inputStream = new FileInputStream("example.schema.json")
JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
InputStream exampleJsonStream = new FileInputStream("example.object.json")
Schema schema = SchemaLoader.load(rawSchema);
schema.validate(new JSONObject(new JSONTokener(exampleJsonStream))) // throws a ValidationException if this object is invalid
