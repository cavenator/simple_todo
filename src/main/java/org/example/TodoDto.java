package org.example;

public class TodoDto {
   private static int nextId = 1;
   public Integer id;
   public String title;
   public String description;

   public TodoDto(){
      this.id = nextId++;
   }

   public TodoDto(String title, String description){
      this.id = nextId++;
      this.title = title;
      this.description = description;
   }

   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TodoDto todoDto = (TodoDto) o;

        if (description != null ? !description.equals(todoDto.description) : todoDto.description != null) return false;
        if (id != null ? !id.equals(todoDto.id) : todoDto.id != null) return false;
        if (title != null ? !title.equals(todoDto.title) : todoDto.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TodoDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
