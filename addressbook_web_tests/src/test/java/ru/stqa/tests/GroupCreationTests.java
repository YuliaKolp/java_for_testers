package ru.stqa.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.stqa.common.CommonFunctions;
import ru.stqa.model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    public static List<GroupData> groupProvider() throws IOException {
        var result = new ArrayList<GroupData>();
//        var json = "";
//        try (var reader = new FileReader("groups.json");
//             var breader = new BufferedReader(reader)
//        ){
//            var line = breader.readLine();
//            while (line != null){
//                json = json + line;
//                line = breader.readLine();
//            }
//        }

        //var json = Files.readString(Paths.get("groups.json"));
        //ObjectMapper mapper = new ObjectMapper();
        var mapper = new XmlMapper();

        //var value = mapper.readValue(new File("groups.json"), new TypeReference<List<GroupData>>() {});
        //var value = mapper.readValue(json, new TypeReference<List<GroupData>>() {});
        var value = mapper.readValue(new File("groups.xml"), new TypeReference<List<GroupData>>() {});
        result.addAll(value);
        return result;
    }


    public static List<GroupData> singleRandomGroup() throws IOException{
        return List.of(new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(20))
                .withFooter(CommonFunctions.randomString(30)));
    }

    public static List<GroupData> negativeGroupProvider() {
        var result = new ArrayList<GroupData>(List.of(
                new GroupData("", "group name'", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("singleRandomGroup")
    public void canCreateGroup(GroupData group) {
        //var oldGroups = app.groups().getList();
        var oldGroups = app.jdbc().getGroupList();

        app.groups().createGroup(group);
        var newGroups = app.jdbc().getGroupList();
        //Sort functions
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var maxId = newGroups.get(newGroups.size()-1).id();

        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);

        // get UI group list
        var newUiGroups = app.groups().getList();
        newUiGroups.sort(compareById);
        // compare UI and DB lists
        var uiGroupsSize = newUiGroups.size();
        var newGroupsSize = newGroups.size();
        if (uiGroupsSize == newGroupsSize){
            for (var i = 0; i < uiGroupsSize; i++){
                var uiId = newUiGroups.get(i).id();
                var id = newGroups.get(i).id();
                var uiName = newUiGroups.get(i).name();
                var name = newGroups.get(i).name();
                Assertions.assertEquals(id, uiId);
                Assertions.assertEquals(name, uiName);
            }
        }
        else {
            throw new RuntimeException(String.format("ERROR: UI groups size '%s' is different to DB groups size '%s'.", uiGroupsSize, newGroupsSize));
        }
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void cannnotCreateMultipleGroups(GroupData group) {
        var oldGroups = app.hbm().getGroupCount();
        app.groups().createGroup(group);
        //app.hbm().createGroup(group); // it is possible to use special chars when object is created not with UI
        var newGroups = app.hbm().getGroupCount();
        Assertions.assertEquals(newGroups, oldGroups);
    }
}




















