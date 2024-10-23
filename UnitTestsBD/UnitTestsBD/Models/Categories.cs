using Supabase.Postgrest.Attributes;
using Supabase.Postgrest.Models;

namespace UnitTestsBD.Models
{
    [Table("Categories")]
    public class Categories : BaseModel
    {
        [PrimaryKey("id")]
        public int Id { get; set; }

        [Column("category")]
        public string Category { get; set; }
    }
}
