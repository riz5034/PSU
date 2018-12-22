using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using WorkHourTracker.Web.Models;
using WorkHourTracker.Web.Infrastructure;
using WorkHourTracker.Model.Entities;
using WorkHourTracker.Model.Exceptions;
using WorkHourTracker.Domain.Domains;
using WorkHourTracker.Domain.Interfaces;
using System.Text;
using Microsoft.AspNetCore.Http.Extensions;

namespace WorkHourTracker.Web.Controllers
{
    /// <summary>
    /// This controller is used to handle user login and account creation
    /// </summary>
    public class HomeController : Controller
    {
        private readonly IUserAccount _IUserAccount;
     
        public HomeController()
        {
            //_ITrackEmployeeHours = new TrackEmployeeHours();
            _IUserAccount = new UserAccountDomain();    
        }

        public IActionResult Index()
        {
            //User is logged in so return the index's View
            return View();
        }

        public IActionResult UserLogin()
        {
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> ProcessLogin(UserLoginInput input)
        {
            //if the ModelState is invalid return the user to the CreateProject page and show them the validation errors
            if (!ModelState.IsValid)
            {
                List<string> errors = ModelState.Values.SelectMany(p => p.Errors.Select(x => x.ErrorMessage)).ToList();
                TempData.Add("ProcessLoginError", errors);
                return RedirectTo("Home", "UserLogin");
            }

            var resultList = new WorkHourTrackerListResult() { Errors = new List<string>(), WorkHourTrackList = new List<dynamic>() };

            try
            {
                //Transform the object into it's Model.Entities counter part for the other layers
                var userLoginDatabaseInput = new UserLoginDatabaseInput() { UserName = input.UserName, Password = input.Password };

                var result = await _IUserAccount.UserLogin(userLoginDatabaseInput);

                //set up a dictionary containing the user's information
                var userDictionary = new Dictionary<string, object>() {
                { "userName", result.UserName },
                { "userPassword", result.UserPassword},
                { "userGuid", result.UserGuid.ToString() },
                { "userRole", result.UserRole },
                { "employeeGuid", result.EmployeeGuid},
                { "firstName", result.FirstName},
                { "lastName", result.LastName}
            };
                //Clear out the TempData before adding to avoid Key collisions
                TempData.Clear();

                //foreach through the userDictionary and add the key/value to the TempData
                foreach (var keyValuePair in userDictionary)
                {
                    TempData.Add(keyValuePair.Key, keyValuePair.Value);
                }

                //Mark all of the data inside TempData for rentention
                TempData.Keep();
            }
            catch (InvalidLoginException)
            {
                resultList.Errors.Add("The user name or password you entered is incorrect, please try again.");
                TempData.Add("LoginErrors", resultList.Errors);

                return RedirectTo("Home", "UserLogin");
            }
            catch (Exception ex)
            {
                resultList.Errors.Add("An unexpected error occured.");
                resultList.Errors.Add($"Exception Message: {ex.Message}");
                resultList.Errors.Add($"Base Exception: {ex.GetBaseException()}");
                TempData.Add("LoginErrors", resultList.Errors);

                return RedirectTo("Home", "UserLogin");
            }

            //Login successful redirect to the index
            return RedirectTo("Home", "Index");
        }

        /// <summary>
        /// Method to sign the user out of the application and return them to the login screen
        /// </summary>
        /// <returns></returns>
        public IActionResult UserSignOut()
        {
            //Since the user is logging out clear the TempData
            TempData.Clear();

            return RedirectToRoute(new
            {
                controller = "Home",
                action = "UserLogin"
            });
        }

        public IActionResult DisplayCreateUserAccountPage()
        {
            return View();
        }

        public async Task<IActionResult> CreateUserAccount(CreateUserAccountInput input)
        {
            var resultList = new WorkHourTrackerListResult()
            {
                Errors = new List<string>(), WorkHourTrackList = new List<dynamic>()
            };

            //If the model state is invalid return set the Errors property of WorkHourTrackListResult object
            if (!ModelState.IsValid)
            {
                resultList.Errors = ModelState.Values.SelectMany(p => p.Errors.Select(x => x.ErrorMessage)).ToList();

                TempData.Add("ModelErrors", resultList.Errors);


                return RedirectTo("Home", "DisplayCreateUserAccountPage");
            }

            try
            {
                //the request is valid so transform to CreateUserAccountDatabaseInput

                var databaseInput = new CreateUserAccountDatabaseInput(input.UserName, input.UserPassword,
                                                                       input.UserRole, input.FirstName,
                                                                       input.LastName);

                //send the new account request to Domain layer
                await _IUserAccount.CreateUserAccount(databaseInput);
            }
            catch (CreateUserNameException)
            {
                resultList.Errors.Add($"The user name: {input.UserName} is already in use. Please, use a different one.");
                TempData.Add("ModelErrors", resultList.Errors);

                return RedirectTo("Home", "DisplayCreateUserAccountPage");
            }
            catch (Exception ex)
            
            {
                resultList.Errors.Add($"An unexpected error has occured.");
                TempData.Add("ModelErrors", resultList.Errors);

                return RedirectTo("Home", "DisplayCreateUserAccountPage");
            }

            TempData.Add("CreateUserAccountSuccess", $"User account for {input.UserName} was successfully created. You may log into the system now.");
            return RedirectTo("Home", "UserLogin");
        }

        /// <summary>
        /// Use this method to direct to another controller / action
        /// </summary>
        /// <param name="controllerName"></param>
        /// <param name="actionMethodName"></param>
        /// <returns></returns>
        public IActionResult RedirectTo(string controllerName, string actionMethodName)
        {
            return RedirectToRoute(new
            {
                controller = controllerName,
                action = actionMethodName
            });
        }

        public IActionResult About()
        {
            ViewData["Message"] = "Your application description page.";
            

            return View();
        }

        public IActionResult Contact()
        {
            ViewData["Message"] = "Your contact page.";

            return View();
        }

        public IActionResult Privacy()
        {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}
