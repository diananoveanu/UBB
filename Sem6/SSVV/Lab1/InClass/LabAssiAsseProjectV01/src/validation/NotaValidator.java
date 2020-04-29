package validation;
import domain.Nota;
import domain.Tema;
import repository.TemaXMLRepository;

public class NotaValidator implements Validator<Nota> {
    private TemaXMLRepository temaXmlRepo;

    public void validate(Nota nota) throws ValidationException {
        if (nota.getID().getObject1() == null || nota.getID().equals("")) {
            throw new ValidationException("ID Student invalid! \n");
        }
        if (nota.getID().getObject2() == null || nota.getID().equals("")) {
            throw new ValidationException("ID Tema invalid! \n");
        }
        if (nota.getNota() < 0 || nota.getNota() > 10) {
            throw new ValidationException("Nota invalida! \n");
        }
        if (nota.getSaptamanaPredare() < 0) {
            throw new ValidationException("Saptamana de predare invalida! \n");
        }
    }

    public int saveTema(String id, String descriere, int deadline, int startline) {
        Tema tema = new Tema(id, descriere, deadline, startline);
        Tema result = temaXmlRepo.save(tema);

        if (result == null) {
            return 1;
        }
        return 0;
    }
}
