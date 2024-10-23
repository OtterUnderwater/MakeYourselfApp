using Supabase.Postgrest.Attributes;
using Supabase.Postgrest.Models;

namespace UnitTestsBD.Models
{
    [Table("Goals")]
    public class Goals : BaseModel
    {
        [PrimaryKey("id")]
        public Guid Id { get; set; }

        [Column("name")]
        public string Name { get; set; }

        [Column("description")]
        public string Description { get; set; }

        [Column("id_sphere")]
        public int IdSphere { get; set; }

        [Column("status")]
        public bool Status { get; set; }

        [Column("id_user")]
        public Guid IdUser { get; set; }
    }

}
