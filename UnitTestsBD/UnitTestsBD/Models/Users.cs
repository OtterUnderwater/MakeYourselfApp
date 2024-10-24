using Supabase.Postgrest.Attributes;
using Supabase.Postgrest.Models;

namespace UnitTestsBD.Models
{
    [Table("Users")]
    public class Users : BaseModel
    {
        [PrimaryKey("id")]
        public Guid Id { get; set; }

        [Column("name")]
        public string? Name { get; set; }

        [Column("login")]
        public string? Login { get; set; }
    }

}
