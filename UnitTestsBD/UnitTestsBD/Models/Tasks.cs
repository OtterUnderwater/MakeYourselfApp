using Supabase.Postgrest.Attributes;
using Supabase.Postgrest.Models;


namespace UnitTestsBD.Models
{
    [Table("Tasks")]
    public class Tasks : BaseModel
    {
        [PrimaryKey("id")]
        public Guid Id { get; set; }

        [Column("id_goal")]
        public Guid IdGoal { get; set; }

        [Column("name_task")]
        public string NameTask { get; set; }

        [Column("description")]
        public string Description { get; set; }

        [Column("status")]
        public bool Status { get; set; }

        [Column("id_category")]
        public int IdCategory { get; set; }
    }
}
