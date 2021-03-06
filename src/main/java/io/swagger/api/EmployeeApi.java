/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.14).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Employee;
import java.util.List;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-08T21:38:04.827Z")

@Api(value = "employee", description = "the employee API")
@RequestMapping(value = "")
public interface EmployeeApi {

    @ApiOperation(value = "Create employee", nickname = "createemployee", notes = "This can only be done by the logged in employee.", tags={ "employee", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation") })
    @RequestMapping(value = "/employee",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> createemployee(@ApiParam(value = "Created employee object" ,required=true )  @Valid @RequestBody Employee body);


    @ApiOperation(value = "Creates list of employees with given input array", nickname = "createemployeesWithArrayInput", notes = "", tags={ "employee", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation") })
    @RequestMapping(value = "/employee/createWithArray",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> createemployeesWithArrayInput(@ApiParam(value = "List of employee object" ,required=true )  @Valid @RequestBody List<Employee> body);


    @ApiOperation(value = "Creates list of employees with given input array", nickname = "createemployeesWithListInput", notes = "", tags={ "employee", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation") })
    @RequestMapping(value = "/employee/createWithList",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> createemployeesWithListInput(@ApiParam(value = "List of employee object" ,required=true )  @Valid @RequestBody List<Employee> body);


    @ApiOperation(value = "Delete employee", nickname = "deleteemployee", notes = "This can only be done by the logged in employee.", tags={ "employee", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid employeename supplied"),
        @ApiResponse(code = 404, message = "employee not found") })
    @RequestMapping(value = "/employee/{employeename}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteemployee(@ApiParam(value = "The name that needs to be deleted",required=true) @PathVariable("employeename") String employeename);


    @ApiOperation(value = "Get employee by employee name", nickname = "getemployeeByName", notes = "", response = Employee.class, tags={ "employee", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Employee.class),
        @ApiResponse(code = 400, message = "Invalid employeename supplied"),
        @ApiResponse(code = 404, message = "employee not found") })
    @RequestMapping(value = "/employee/{employeename}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Employee> getemployeeByName(@ApiParam(value = "The name that needs to be fetched. Use employee1 for testing. ",required=true) @PathVariable("employeename") String employeename);


    @ApiOperation(value = "Logs employee into the system", nickname = "loginemployee", notes = "", response = String.class, tags={ "employee", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = String.class),
        @ApiResponse(code = 400, message = "Invalid employeename/password supplied") })
    @RequestMapping(value = "/employee/login",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<String> loginemployee(@NotNull @ApiParam(value = "The employee name for login", required = true) @Valid @RequestParam(value = "employeename", required = true) String employeename,@NotNull @ApiParam(value = "The password for login in clear text", required = true) @Valid @RequestParam(value = "password", required = true) String password);


    @ApiOperation(value = "Logs out current logged in employee session", nickname = "logoutemployee", notes = "", tags={ "employee", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation") })
    @RequestMapping(value = "/employee/logout",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Void> logoutemployee();


    @ApiOperation(value = "Updated employee", nickname = "updateemployee", notes = "This can only be done by the logged in employee.", tags={ "employee", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid employee supplied"),
        @ApiResponse(code = 404, message = "employee not found") })
    @RequestMapping(value = "/employee/{employeename}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateemployee(@ApiParam(value = "name that need to be updated",required=true) @PathVariable("employeename") String employeename,@ApiParam(value = "Updated employee object" ,required=true )  @Valid @RequestBody Employee body);

}
