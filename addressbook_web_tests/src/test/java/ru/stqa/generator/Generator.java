package ru.stqa.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import ru.stqa.common.CommonFunctions;
import ru.stqa.model.ContactData;
import ru.stqa.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// Config!!!!
// --type groups --output groups.json --format json --count 3
// --type contacts --output contacts.xml --format xml --count 3
public class Generator {

    @Parameter(names={"--type", "-t"})
    String type;

    @Parameter(names={"--output", "-o"})
    String output;

    @Parameter(names={"--format", "-f"})
    String format;

    @Parameter(names={"--count", "-c"})//-n
    int count;

    public static void main(String[] args)  throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run()  throws IOException {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if ("groups".equals(type)){
            return generateGroups();
        } else if ("contacts".equals(type)){
            return generateContacts();
        } else {
            throw new IllegalArgumentException("Unknown data type " + type);
        }

    }

    private Object generateGroups() {
        var result = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++){
            result.add(new GroupData()
                    .withName(CommonFunctions.randomString(i*10))
                    .withHeader(CommonFunctions.randomString(i*10))
                    .withFooter(CommonFunctions.randomString(i*10)));
        }
        return result;
    }

    private Object generateContacts() {
        var result = new ArrayList<ContactData>();
        for (int i = 0; i < 5; i++){
            result.add(new ContactData()
                    .withFirstName(CommonFunctions.randomString(i*5))
                    .withMiddleName(CommonFunctions.randomString(i*5))
                    .withLastName(CommonFunctions.randomString(i*5)));
        }
        return result;
    }

    private void save(Object data) throws IOException {
        // check format
        if (!("json".equals(format)) & (!("yaml".equals(format))) & (!("xml".equals(format))))
            {
            throw new IllegalArgumentException("Unknown data format " + format);
        }
        // generate data to test
        if ("json".equals(format)){
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            var json = mapper.writeValueAsString(data);

            try (var writer = new FileWriter(output)) {
                writer.write(json);
            }
            //writer.close();
            //mapper.writeValueAsString(new File(output), data);
        }
        if ("yaml".equals(format)) {
            var mapper = new YAMLMapper();
            mapper.writeValue(new File(output), data);
        }
        if ("xml".equals(format))
        {
            var mapper = new XmlMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(output), data);
        }

    }
}
