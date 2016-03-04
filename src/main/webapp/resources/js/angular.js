var app1 = angular.module('app1',[]);

app1.controller("ctrl1", function($scope){
    $scope.navSelection = "addEmployee";
    $scope.addNavSelection = "selected";
    $scope.getNavSelection = "";

    /**
     * initially start on add employee page
     */
    // jquery to show the add employee divs
    $("#addEmployeeDiv").show();

    // jquery to hide the employee divs
    $("#getEmployeeDiv").hide();

    /**
     * FUNCTIONS
     */
    
    $scope.updateNavSelection = function(){
        $scope.navSelection = "getEmployee";
    }
    
    $scope.addEmployeeNavSelection = function(){
        $scope.addNavSelection = "selected";
        $scope.getNavSelection = "";

        // jquery to show the add employee divs
        $("#addEmployeeDiv").show();

        // jquery to hide the employee divs
        $("#getEmployeeDiv").hide();

    }
    
    $scope.getEmployeeNavSelection = function(){
        $scope.addNavSelection = "";
        $scope.getNavSelection = "selected";

        // jquery to hide the add employee divs
        $("#addEmployeeDiv").hide();

        // jquery to show the employee divs
        $("#getEmployeeDiv").show();
    }
});

