using Supabase;
using Supabase.Gotrue;
using Supabase.Postgrest.Responses;
using System.Net;
using System.Reflection;
using UnitTestsBD.Models;

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
        public async Task<ModeledResponse<Tasks>> GetTasks() => await supabase.From<Tasks>().Get();
        public async Task<ModeledResponse<Spheres>> GetUserSpheres(Guid id) => await supabase.From<Spheres>().Where(x => x.IdUser == id).Get();
		public async Task<ModeledResponse<Users>> GetUsers() => await supabase.From<Users>().Get();
		public async Task<Session?>? CreateUser(string email, string password) => await supabase.Auth.SignUp(email, password);       
		public async Task<ModeledResponse<Goals>> AddGoal(Goals model) => await supabase.From<Goals>().Upsert(model);
		public async Task<Session?> SignIn(string email, string password) => await supabase.Auth.SignIn(email, password);
		public async Task<ModeledResponse<Goals>> UpdateGoal(Guid id, bool newStatus) => await supabase.From<Goals>().Where(x => x.Id == id).Set(x => x.Status, newStatus).Update();
		public async Task? DeleteGoal(Guid id) => await supabase.From<Goals>().Where(x => x.Id == id).Delete();

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
			var session = SignIn("testUpdateDelete@bk.ru", "testUpdateDelete123");
			session.Wait();
			var user = session.Result.User;
			Guid id = Guid.Parse(user.Id);
			var temp = GetUserSpheres(id);
            temp.Wait();
            var spheres = temp.Result.Models;
            Assert.AreEqual(spheres.Count, 6);
        }

		[TestMethod]
		public void GetUsers_TableUsers_ListUsersReturned()
		{
			var result = GetUsers();
			result.Wait();
			var users = result.Result.Models;
			Assert.IsNotNull(users);
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
		
        [TestMethod]
		public void AddGoal_TableGoals_CountReturned()
		{
			var session = SignIn("testUpdateDelete@bk.ru", "testUpdateDelete123");
			session.Wait();
			var result1 = GetGoals();
			result1.Wait();
            var temp = GetSpheres();
			temp.Wait();
            Guid id = Guid.Parse(session.Result.User.Id);
			var sphere = temp.Result.Models.FirstOrDefault(x => x.IdUser == id).Id;    
			var goal = new Goals
			{
				Name = "Тестовая цель",
				Description = "цель 1",
				IdSphere = sphere,
				Status = false,
				IdUser = Guid.Parse(session.Result.User.Id)
			};
            var result2 = AddGoal(goal);
			result2.Wait();
            int count1 = result1.Result.Models.Count + 1;
			int count2 = result2.Result.Models.Count;
			Assert.AreEqual(count1, count2);
		}

		[TestMethod]
        public void UpdateGoal_TableGoals_UpdateReturned()
		{
			var session = SignIn("testUpdateDelete@bk.ru", "testUpdateDelete123");
			session.Wait();

			var user = session.Result.User;
            Guid id = Guid.Parse(user.Id);

			var result = UpdateGoal(id, true);
            result.Wait();
            HttpStatusCode code = result.Result.ResponseMessage.StatusCode;
		
			Assert.AreEqual(code, HttpStatusCode.OK);
        }

		[TestMethod]
        public void DeleteGoal_TableGoals_DeleteReturned()
         {
			var session = SignIn("testUpdateDelete@bk.ru", "testUpdateDelete123");
			session.Wait();
			Guid id = Guid.Parse(session.Result.User.Id);
			var goals = GetGoals();
			goals.Wait();
			Guid firstIdGoals = goals.Result.Models.First(x => x.IdUser == id).Id;

			var result2 = DeleteGoal(firstIdGoals);
            result2.Wait();

            Assert.IsTrue(result2.IsCompleted);
         }

	}
}