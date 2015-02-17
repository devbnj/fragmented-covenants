var SearcherController = angular.module("SearcherController", []);

SearcherController.controller("ListController", ['$scope','$http', function($scope, $http) {
			$scope.queryForm = {};
			$scope.queryForm.queryString = 'krishna AND upanishad';
			$scope.queryForm.responseType = 'json';
			var qry1 = $scope.queryForm.queryString.toUpperCase(); 
			var format1 = $scope.queryForm.responseType;
			$scope.queryForm.submitTheForm = function(item, event) {
				console.log("--> Submitting form");
				console.log("--> queryString "+queryString.value);
				qry1 = queryString.value.toString().toUpperCase();
				format1 = responseType.value.toString();
	
			$http.get('/Searcher/rest/searcher/'+qry1+'/'+format1).success (function(data) {
				console.log("--> Query "+qry1);
				$scope.searcherVariable = data;
				$scope.askSearcher = 'query';
			}); 
			};

			$http.get('/Searcher/rest/searcher/'+qry1+'/'+format1).success (function(data) {
				console.log("--> Query "+qry1);
				$scope.searcherVariable = data;
				$scope.askSearcher = 'query';
			}); 
		}]
);