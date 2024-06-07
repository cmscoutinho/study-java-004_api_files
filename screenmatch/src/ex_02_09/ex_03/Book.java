package ex_02_09.ex_03;

record Book(String title, String author, Publisher publisher) {
    @Override
    public String toString() {
        return "Title: " + title + " | Author: " + author + " | Publisher: " + publisher;
    }
}

record Publisher(String name, String city, boolean isAcademic) {
    @Override
    public String toString() {
        return "Name: " + name + " | City: " + city + " | Academic: " + isAcademic;
    }
}


