using Supabase;
using Supabase.Gotrue;
using Supabase.Interfaces;
using Supabase.Postgrest;
using Supabase.Postgrest.Responses;
using System;
using System.Reflection;
using UnitTestsBD.Models;
using static Supabase.Postgrest.QueryOptions;

namespace UnitTestsBD
{
    [TestClass]
    public class UnitTests
    {
        #region [Supabase]
        static string _url = "https://moewagojykcyqqwzqbrm.supabase.co";
        static string _key = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im1vZXdhZ29qeWtjeXFxd3pxYnJtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjkxNjE1MjUsImV4cCI6MjA0NDczNzUyNX0.gs77FbNJKezPyfnY0aMOL-AzbaH5xcnQ_g-L9tHFTKw";
        static SupabaseOptions _options = new SupabaseOptions
        {
            AutoConnectRealtime = true
        };
        Supabase.Client supabase = new Supabase.Client(_url, _key, _options);

        public async void Initialize()
        {
            await supabase.InitializeAsync();
        }

        public UnitTests()
        {
            Initialize();
        }
        #endregion

        public async Task<ModeledResponse<Categories>> GetCategories() => await supabase.From<Categories>().Get();
        public async Task<ModeledResponse<Goals>> GetGoals() => await supabase.From<Goals>().Get();
        public async Task<ModeledResponse<Spheres>> GetSpheres() => await supabase.From<Spheres>().Get();
        public async Task<ModeledResponse<Spheres>> GetUserSpheres(Guid id) => await supabase.From<Spheres>().Where(x => x.IdUser == id).Get();
        public async Task<ModeledResponse<Tasks>> GetTasks() => await supabase.From<Tasks>().Get();
        public async Task<ModeledResponse<Users>> GetUsers() => await supabase.From<Users>().Get();

        public async Task<Session?>? CreateUser(string email, string password) => await supabase.Auth.SignUp(email, password);
        
        public async void AddUser(Users user) => await supabase.From<Users>().Insert(user);


        [TestMethod]
        public void CreateUser_Auth_UserReturned()
        {
            string email = "test1234@bk.ru";
            string password = "test1234";
            var session = CreateUser(email, password);
            session.Wait();
            var user = session.Result.User;
            Assert.IsNotNull(user);
        }

        [TestMethod]
        public void GetUserSpheres_TableSpheres_Spheres6DefaultReturned()
        {
            string email = "test1111@bk.ru";
            string password = "test1111";
            var session = CreateUser(email, password);
            session.Wait();
            var result = session.Result.User;
            var user = new Users
            {
                Id = Guid.Parse(result.Id),
                Name = "Test",
                Login = result.Email
            };
            AddUser(user);
            var temp = GetUserSpheres(user.Id);
            temp.Wait();
            var spheres = temp.Result.Models;
            Assert.AreEqual(spheres.Count, 6);
        }

        [TestMethod]
        public void GetCategories_TableCategories_ListCategoriesReturned()
        {
            var result = GetCategories();
            result.Wait();
            var categories = result.Result.Models;       
            Assert.IsNotNull(categories);         
        }

        [TestMethod]
        public void GetGoals_TableGoals_ListGoalsReturned()
        {
            var result = GetGoals();
            result.Wait();
            var goals = result.Result.Models;
            Assert.IsNotNull(goals);
        }

        [TestMethod]
        public void GetSpheres_TableSpheres_ListSpheresReturned()
        {
            var result = GetSpheres();
            result.Wait();
            var spheres = result.Result.Models;
            Assert.IsNotNull(spheres);
        }

        [TestMethod]
        public void GetTasks_TableTasks_ListTasksReturned()
        {
            var result = GetTasks();
            result.Wait();
            var tasks = result.Result.Models;
            Assert.IsNotNull(tasks);
        }
    }
}