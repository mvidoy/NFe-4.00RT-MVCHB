import fitz
doc = fitz.open("C:/Desenv/JavaProject/Homologacao/NFe-4.00RT-MVCHB/REFORMA_TRIBUTARIA/Pantano/ORIENTACAO TRIBUTARIA- CAMAR.pdf")
for i, page in enumerate(doc):
    pix = page.get_pixmap(dpi=200)
    out = "C:/Desenv/JavaProject/Homologacao/NFe-4.00RT-MVCHB/REFORMA_TRIBUTARIA/Pantano/page_" + str(i+1) + ".png"
    pix.save(out)
    print("Page " + str(i+1) + " saved")
