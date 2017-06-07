'use strict';

angular.module('crudApp').controller('UserController',
    ['UserService', '$scope',  function( UserService, $scope) {

        var self = this;
        $scope.user = {};
        $scope.users=[];

        $scope.submit = submit;
        $scope.getAllUsers = getAllUsers;
        $scope.createUser = createUser;
        $scope.updateUser = updateUser;
        $scope.removeUser = removeUser;
        $scope.editUser = editUser;
        $scope.reset = reset;

        $scope.successMessage = '';
        $scope.errorMessage = '';
        $scope.done = false;

        $scope.onlyIntegers = /^\d+$/;
        $scope.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if ($scope.user.id === undefined || $scope.user.id === null) {
                console.log('Saving New User', $scope.user);
                createUser($scope.user);
            } else {
                updateUser($scope.user, $scope.user.id);
                console.log('User updated with id ', $scope.user.id);
            }
        }

        function createUser(user) {
            console.log('About to create user');
            UserService.createUser(user)
                .then(
                    function (response) {
                        console.log('User created successfully');
                        $scope.successMessage = 'User created successfully';
                        $scope.errorMessage='';
                        $scope.done = true;
                        $scope.user={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating User');
                        $scope.errorMessage = 'Error while creating User: ' + errResponse.data.errorMessage;
                        $scope.successMessage='';
                    }
                );
        }


        function updateUser(user, id){
            console.log('About to update user');
            UserService.updateUser(user, id)
                .then(
                    function (response){
                        console.log('User updated successfully');
                        $scope.successMessage='User updated successfully';
                        $scope.errorMessage='';
                        $scope.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating User');
                        $scope.errorMessage='Error while updating User '+errResponse.data;
                        $scope.successMessage='';
                    }
                );
        }


        function removeUser(id){
            console.log('About to remove User with id '+id);
            UserService.removeUser(id)
                .then(
                    function(){
                        console.log('User '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing user '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllUsers(){
            return UserService.getAllUsers();
        }

        function editUser(id) {
            $scope.successMessage='';
            $scope.errorMessage='';
            UserService.getUser(id).then(
                function (user) {
                    $scope.user = user;
                },
                function (errResponse) {
                    console.error('Error while removing user ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            $scope.successMessage='';
            $scope.errorMessage='';
            $scope.user={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }
    ]);