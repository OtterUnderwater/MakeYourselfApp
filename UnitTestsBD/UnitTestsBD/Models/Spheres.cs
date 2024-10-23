using Supabase.Postgrest.Attributes;
using Supabase.Postgrest.Models;

namespace UnitTestsBD.Models
{
    [Table("Spheres")]
    public class Spheres : BaseModel
    {
        [PrimaryKey("id")]
        public int Id { get; set; }

        [Column("id_user")]
        public Guid IdUser { get; set; }

        [Column("name")]
        public string Name { get; set; }     
    }
}
