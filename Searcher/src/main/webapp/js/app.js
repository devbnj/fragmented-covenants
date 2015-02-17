var SearcherApp = angular.module('SearcherApp', [
  'ngRoute',
  'SearcherController'
]);

SearcherApp.config(['$routeProvider', function($routeProvider) {
  $routeProvider.
  when('/list', {
    templateUrl: 'includes/searcher.html',
    controller: 'ListController'
  }).
  otherwise({
    redirectTo: '/list'
  });
}]);