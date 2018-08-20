package com.wintelia.basewebController;


import com.wintelia.baseService.DataBaseOperateService;
import com.wintelia.projectModel.DatabaseModel;
import com.wintelia.projectModel.TableSchemaModel;
import com.wintelia.projectModel.TemplateViewModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DataBaseOperateController {

    @Autowired
    private DataBaseOperateService dataBaseOperateService;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @RequestMapping("/codemaker")
    public ModelAndView Codemaker() {
        List<DatabaseModel> tables = dataBaseOperateService.GetAllTables();
        List<TableSchemaModel> columns = dataBaseOperateService.GetTableSchema(tables.get(0).getTablename());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tableslist", tables);
        modelAndView.addObject("columns", columns);
        modelAndView.setViewName("codemaker");
        return modelAndView;
    }

    @RequestMapping(value = "/getcolumn", method = RequestMethod.POST)
    @ResponseBody
    public List<TableSchemaModel> getTableschema(String tablename) {
        return dataBaseOperateService.GetTableSchema(tablename);
    }

    @RequestMapping(value = "/bulidcode", method = RequestMethod.POST)
    @ResponseBody
    public String codemakerbuild(@RequestParam("tablename") String tablename, @RequestParam("optype") String optype,
                                 @RequestParam("columns[]") List<String> columns) {
        String newtablename = tablename.substring(0, 1).toUpperCase() + tablename.substring(1);
        String templatename = "";
        switch (optype) {
            case "1":
                templatename = "ModelFile.ftl";
                break;
            case "3":
                templatename = "DaoFile.ftl";
                break;
            case "2":
                templatename = "Mapper.ftl";
                break;
            default:
                break;
        }
        List<TemplateViewModel> templateViewdata = new ArrayList<>();
        for (int i = 0; i < columns.size(); i++) {
            String[] columninfo = columns.get(i).split("-",5);
            TemplateViewModel tempmodel = new TemplateViewModel();
            tempmodel.setOrder(Integer.valueOf(columninfo[0].toString()));
            tempmodel.setLowerName(columninfo[1]);
            tempmodel.setUpperName(columninfo[1].substring(0, 1).toUpperCase() + columninfo[1].substring(1));
            if (!columninfo[2].equals("")) {
                switch (columninfo[2].toLowerCase()) {
                    case "varchar":
                        tempmodel.setDataType("String");
                        break;
                    case "bigint":
                        tempmodel.setDataType("long");
                        break;
                    case "int":
                        tempmodel.setDataType("int");
                        break;
                    case "date":
                        tempmodel.setDataType("Date");
                        break;
                    case "datetime":
                        tempmodel.setDataType("Date");
                        break;
                    case "decimal":
                        tempmodel.setDataType("BigDecimal");
                        break;
                    default:
                        tempmodel.setDataType("String");
                        break;
                }
            }
            if (!columninfo[3].equals("")) {
                tempmodel.setDescription(columninfo[3]);
            }
            if (!columninfo[4].equals("")) {
                tempmodel.setExtend(columninfo[4]);
            }
            templateViewdata.add(tempmodel);
        }
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        Template template = null;
        Map data = new HashMap();
        data.put("tablename", newtablename);
        data.put("columns", templateViewdata);
        StringWriter writer = new StringWriter();
        try {
            template = configuration.getTemplate(templatename);
            template.process(data, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return writer.toString();
    }
}
