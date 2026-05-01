package com.frontend.util;

import com.backend.services.Conection;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class UpdateTablesDialog extends JDialog {

    private JTextPane logArea;  // Definido como JTextPane para suportar estilos
    private Conection connectionService;

    public UpdateTablesDialog(Frame owner, Conection connectionService) throws BadLocationException {
        super(owner, "Atualizando Tabelas", true);
        this.connectionService = connectionService;
        // Inicializa JTextPane ao invés de JTextArea para suportar estilos
        logArea = new JTextPane();
        logArea.setEditable(false);
        logArea.setPreferredSize(new Dimension(1400, 600));
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Define a fonte

        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane, BorderLayout.CENTER);

        // Adicionando um botão para fechar o diálogo
        JButton closeButton = new JButton("Fechar");
        closeButton.setEnabled(false);  // Inicialmente desabilitado até o processo terminar
        closeButton.addActionListener(e -> dispose());
        add(closeButton, BorderLayout.SOUTH);

        setSize(900, 400);
        setLocationRelativeTo(owner);

        updateTable();
        // Iniciar o processo de atualização em uma thread separada
        new Thread(() -> {
            try {
                boolean success = startUpdateProcess();  // Verificar o sucesso da operação
                if (success) {
                    logMessage("Atualização concluída com sucesso.");
                } else {
                    logMessage("Falha na atualização das tabelas.");
                }
                closeButton.setEnabled(true); // Habilitar o botão quando o processo terminar
            } catch (BadLocationException ex) {
                Logger.getLogger(UpdateTablesDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    private boolean startUpdateProcess() throws BadLocationException {
        StyledDocument doc = logArea.getStyledDocument(); // Obtém o StyledDocument
        Style style = logArea.getStyle(StyleContext.DEFAULT_STYLE); // Usa o estilo padrão

        try {
            doc.insertString(doc.getLength(), "Aguarde enquanto o sistema atualiza as tabelas do sistema...\n", style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        pack();
        setLocationRelativeTo(null);  // Centraliza o diálogo na tela

        // Array com tabelas que serão processadas
        String[][] tablesAndSequences = {
            {"cbmotivo", "cbmotivo_id_seq", "cbocorr_id"},
            {"cbocorr", "cbocorr_id_seq", null},
            {"m2pedven", "m2pedven_id_seq", null},
            {"m2iteped", "m2iteped_id_seq", "m2pedven_id"},
            {"m2pvparc", "m2pvparc_id_seq", "m2pedven_id"},
            {"m2predup", "m2predup_id_seq", "m2pedven_id"},
            {"caentpro", "caentpro_id_seq", null},
            {"caiteent", "caiteent_id_seq", "caentpro_id"},
            {"caiteend", "caiteend_id_seq", "caentpro_id"},
            {"caapdupl", "caapdupl_id_seq", "caentpro_id"},
            {"caapdupl_previa", "caapdupl_previa_id_seq", "caentpro_id"},
            {"canfenotfis", "canfenotfis_id_seq", null},
            {"canfeitenot", "canfeitenot_id_seq", "canfenotfis_id"},
            //{"nfe_infprot", "nfe_infprot_id_seq", "canfenotfis_id"},
            {"canfeduplic", "canfeduplic_id_seq", "canfenotfis_id"},
            {"canfeirdupl", "canfeirdupl_id_seq", "canfenotfis_id"},
            {"canfeirdupl", "canfeirdupl_id_seq", "canfeduplic_id"},
            {"caipdupl", "caipdupl_id_seq", "caentpro_id"},
            {"caipdupl", "caipdupl_id_seq", "caapdupl_id"}
        };

        // Relacionamentos entre tabelas e colunas relacionadas
        Map<String, TableRelation> tableRelations = new HashMap<>();
        tableRelations.put("cbmotivo",
                new TableRelation("cbmotivo", "cbocorr_id", "cbocorr", null,
                        Arrays.asList("BANCO", "CODIGO"),
                        Arrays.asList("BANCO", "FKCBOCORR")));

        // Relacionamento entre 'cbmotivo' e 'cbocorr':
        // Atualiza a coluna 'cbocorr_id' na tabela 'cbmotivo' com o 'id' de 'cbocorr'.
        // As chaves de ligação entre as tabelas são:
        //  - 'BANCO' de ambas as tabelas.
        //  - 'CODIGO' de 'cbocorr' com 'FKCBOCORR' de 'cbmotivo'.
        tableRelations.put("m2iteped",
                new TableRelation("m2iteped", "m2pedven_id", "m2pedven", "m2iteped",
                        Arrays.asList("PED_CODI"),
                        Arrays.asList("ITE_CODI")));

        tableRelations.put("m2pvparc",
                new TableRelation("m2pvparc", "m2pedven_id", "m2pedven", "m2pvparc",
                        Arrays.asList("PED_CODI"),
                        Arrays.asList("PAR_CODI")));

        tableRelations.put("m2predup",
                new TableRelation("m2predup", "m2pedven_id", "m2pedven", "m2predup",
                        Arrays.asList("PED_CODI"),
                        Arrays.asList("PAR_CODI")));

        tableRelations.put("caiteent",
                new TableRelation("caiteent", "caentpro_id", "caentpro", "caiteent",
                        Arrays.asList("EPR_NUME", "EPR_FORN"),
                        Arrays.asList("IEN_NUME", "IEN_FORN")));

        tableRelations.put("caiteend",
                new TableRelation("caiteend", "caentpro_id", "caentpro", "caiteend",
                        Arrays.asList("EPR_NUME", "EPR_FORN"),
                        Arrays.asList("IEN_NUME", "IEN_FORN")));

        tableRelations.put("caapdupl",
                new TableRelation("caapdupl", "caentpro_id", "caentpro", "caapdupl",
                        Arrays.asList("EPR_NUME", "EPR_FORN"),
                        Arrays.asList("PAR_CODI", "PAR_FORN")));

        tableRelations.put("caapdupl_previa",
                new TableRelation("caapdupl_previa", "caentpro_id", "caentpro", "caapdupl_previa",
                        Arrays.asList("EPR_NUME", "EPR_FORN"),
                        Arrays.asList("PAR_CODI", "PAR_FORN")));

        tableRelations.put("canfeitenot",
                new TableRelation("canfeitenot", "canfenotfis_id", "canfenotfis", "canfeitenot",
                        Arrays.asList("NOT_NOTA"),
                        Arrays.asList("INF_CODI")));

        tableRelations.put("nfe_infprot",
                new TableRelation("nfe_infprot", "canfenotfis_id", "canfenotfis", "nfe_infprot",
                        Arrays.asList("NOT_NOTA"),
                        Arrays.asList("INF_CODI")));

        tableRelations.put("canfeduplic",
                new TableRelation("canfeduplic", "canfenotfis_id", "canfenotfis", "canfeduplic",
                        Arrays.asList("NOT_NOTA"),
                        Arrays.asList("PAR_CODI")));

        tableRelations.put("canfeirdupl",
                new TableRelation("canfeirdupl", "canfenotfis_id", "canfenotfis", "canfeirdupl",
                        Arrays.asList("NOT_NOTA"),
                        Arrays.asList("IRE_CODI")));

        tableRelations.put("canfeirdupl",
                new TableRelation("canfeirdupl", "canfeduplic_id", "canfenotfis", "canfeirdupl",
                        Arrays.asList("NOT_NOTA"),
                        Arrays.asList("IRE_CODI")));

        tableRelations.put("caipdupl",
                new TableRelation("caipdupl", "caentpro_id", "caentpro", "caipdupl",
                        Arrays.asList("EPR_NUME", "EPR_FORN"),
                        Arrays.asList("IPG_CODI", "IPG_FORN")));

        tableRelations.put("caipdupl",
                new TableRelation("caipdupl", "caapdupl_id", "caapdupl", "caipdupl",
                        Arrays.asList("PAR_CODI", "PAR_FORN", "PAR_PARC"),
                        Arrays.asList("IPG_CODI", "IPG_FORN", "IPG_PARC")));

        // Relacionamento entre 'm2iteped' e 'm2pedven':
        // Atualiza a coluna 'm2pedven_id' na tabela 'm2iteped' com o 'id' de 'm2pedven'.
        // As chaves de ligação entre as tabelas são:
        //  - 'PED_CODI' de 'm2pedven' com 'ITE_CODI' de 'm2iteped'.
        // Iterar sobre o array de tabelas
        boolean updateSuccess = true;
        for (String[] tableInfo : tablesAndSequences) {
            String tableName = tableInfo[0];
            String sequenceName = tableInfo[1];
            String additionalColumn = tableInfo[2];

            // Atualizar a tabela
            boolean tableUpdated = updateTable(tableName, sequenceName, additionalColumn, tableRelations);
            doc = logArea.getStyledDocument(); // Obtém o StyledDocument
            style = logArea.getStyle(StyleContext.DEFAULT_STYLE); // Usa o estilo padrão

            if (tableUpdated) {
                // Ajustar a sequência após atualizar a tabela
                boolean sequenceAdjusted = adjustSequenceForTable(tableName, sequenceName);
                if (sequenceAdjusted) {
                    try {
                        doc.insertString(doc.getLength(), "Sequência da tabela '" + tableName + "' ajustada com sucesso.\n", style);
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        Style errorStyle = logArea.addStyle("Error", null);
                        StyleConstants.setForeground(errorStyle, Color.RED); // Define o estilo com cor vermelha
                        doc.insertString(doc.getLength(), "Falha ao ajustar a sequência da tabela '" + tableName + "'.\n", errorStyle);
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
                    updateSuccess = false;
                }
            } else {
                try {
                    Style errorStyle = logArea.addStyle("Error", null);
                    StyleConstants.setForeground(errorStyle, Color.RED); // Define o estilo com cor vermelha
                    doc.insertString(doc.getLength(), "Falha ao atualizar a tabela '" + tableName + "'.\n", errorStyle);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
                updateSuccess = false;
            }

        }
        return updateSuccess;
    }

    // Classe auxiliar para armazenar correlações de tabelas
    public class TableRelation {

        private String tableName;          // Nome da tabela que será atualizada
        private String updateColumn;       // Coluna da tabela que será atualizada
        private String relatedTable;       // Tabela relacionada para o JOIN
        private String dynamicTable;       // Tabela dinâmica (exemplo: caiteped), usada em joins
        private List<String> foreignKeyColumns;   // Colunas da tabela relacionada usadas no JOIN (suporte para até 3)
        private List<String> relatedColumns;      // Colunas da tabela atual usadas para o JOIN (suporte para até 3)

        // Constructor atualizado para múltiplas chaves
        public TableRelation(String tableName, String updateColumn, String relatedTable, String dynamicTable, List<String> foreignKeyColumns, List<String> relatedColumns) {
            this.tableName = tableName;
            this.updateColumn = updateColumn;
            this.relatedTable = relatedTable;
            this.dynamicTable = dynamicTable;
            this.foreignKeyColumns = foreignKeyColumns;
            this.relatedColumns = relatedColumns;
        }

        // Getters
        public String getTableName() {
            return tableName;
        }

        public String getUpdateColumn() {
            return updateColumn;
        }

        public String getRelatedTable() {
            return relatedTable;
        }

        public String getDynamicTable() {
            return dynamicTable; // Getter para a tabela dinâmica
        }

        public List<String> getForeignKeyColumns() {
            return foreignKeyColumns;
        }

        public List<String> getRelatedColumns() {
            return relatedColumns;
        }

        // Setters
        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public void setUpdateColumn(String updateColumn) {
            this.updateColumn = updateColumn;
        }

        public void setRelatedTable(String relatedTable) {
            this.relatedTable = relatedTable;
        }

        public void setDynamicTable(String dynamicTable) {
            this.dynamicTable = dynamicTable;
        }

        public void setForeignKeyColumns(List<String> foreignKeyColumns) {
            this.foreignKeyColumns = foreignKeyColumns;
        }

        public void setRelatedColumns(List<String> relatedColumns) {
            this.relatedColumns = relatedColumns;
        }
    }

    // Método genérico para atualizar qualquer tabela de forma dinâmica
    private boolean updateTable(String tableName, String sequenceName, String additionalColumn, Map<String, TableRelation> tableRelations) throws BadLocationException {
        List<String> queries = new ArrayList<>();

        // Verificar se a coluna 'id' existe, se não, adicionar
        queries.add("DO $$ BEGIN IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='" + tableName + "' AND column_name='id') THEN "
                + "ALTER TABLE " + tableName + " ADD id BIGINT UNIQUE; END IF; END $$;");

        // Verificar se há uma coluna adicional para adicionar (ex: m2pedven_id)
        if (additionalColumn != null) {
            queries.add("DO $$ BEGIN IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='" + tableName + "' AND column_name='" + additionalColumn + "') THEN "
                    + "ALTER TABLE " + tableName + " ADD " + additionalColumn + " BIGINT; END IF; END $$;");
        }

        // Gerar as sequências e atualizações
        queries.add("DROP SEQUENCE IF EXISTS " + sequenceName + " CASCADE;");
        queries.add("CREATE SEQUENCE " + sequenceName + " START 1;");
        queries.add("ALTER TABLE " + tableName + " ALTER COLUMN id SET DEFAULT NEXTVAL('" + sequenceName + "');");
        queries.add("UPDATE " + tableName + " SET id = NEXTVAL('" + sequenceName + "') WHERE id IS NULL;");

        // Verificar se a tabela possui uma correlação para realizar o JOIN dinâmico
        TableRelation relation = tableRelations.get(tableName);
        if (relation != null) {
            // Certifique-se de que a tabela relacionada também tenha a coluna 'id'
            queries.add("DO $$ BEGIN IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='" + relation.getRelatedTable() + "' AND column_name='id') THEN "
                    + "ALTER TABLE " + relation.getRelatedTable() + " ADD id BIGINT UNIQUE; END IF; END $$;");

            // Construção dinâmica da query de atualização com até três colunas de chave
            StringBuilder whereClause = new StringBuilder();
            for (int i = 0; i < relation.getForeignKeyColumns().size(); i++) {
                if (i > 0) {
                    whereClause.append(" AND ");
                }
                whereClause.append("c.").append(relation.getForeignKeyColumns().get(i))
                        .append(" = ")
                        .append(tableName).append(".").append(relation.getRelatedColumns().get(i));
            }

            String updateQuery;
            if (relation.getDynamicTable() != null) {
                // Com tabela dinâmica
                updateQuery = String.format(
                        "UPDATE %s SET %s = c.id FROM (SELECT * FROM %s) AS c WHERE %s;",
                        tableName,
                        relation.getUpdateColumn(),
                        relation.getRelatedTable(),
                        whereClause.toString()
                );
            } else {
                // Sem tabela dinâmica
                updateQuery = String.format(
                        "UPDATE %s SET %s = c.id FROM (SELECT * FROM %s) AS c WHERE %s;",
                        tableName,
                        relation.getUpdateColumn(),
                        relation.getRelatedTable(),
                        whereClause.toString()
                );
            }

            queries.add(updateQuery);
        }

        // Executar as queries para essa tabela
        return executeQueries(queries);
    }

    private boolean executeQueries(List<String> queries) throws BadLocationException {
        Connection conn = connectionService.conectaBs();  // Usando JDBC
        if (conn == null) {
            logMessage("Falha ao conectar ao banco de dados. Verifique as configurações.");
            return false; // Retornar falso se a conexão falhar
        }

        PreparedStatement pstmt = null;
        try {
            for (String query : queries) {
                Style errorStyle = logArea.addStyle("Error", null);
                StyledDocument doc = logArea.getStyledDocument(); // Obtém o StyledDocument
                Style style = logArea.getStyle(StyleContext.DEFAULT_STYLE); // Usa o estilo padrão
                doc.insertString(doc.getLength(), "Executando: " + query + "\n", errorStyle);
                pstmt = conn.prepareStatement(query);
                pstmt.executeUpdate();
            }
            return true; // Retornar verdadeiro se tudo ocorrer bem
        } catch (SQLException e) {
            Style errorStyle = logArea.addStyle("Error", null);
            StyledDocument doc = logArea.getStyledDocument(); // Obtém o StyledDocument
            Style style = logArea.getStyle(StyleContext.DEFAULT_STYLE); // Usa o estilo padrão
            doc.insertString(doc.getLength(), "Erro ao executar query: " + e.getMessage() + "\n", errorStyle);
            e.printStackTrace();
            return false; // Retornar falso em caso de erro
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                Style errorStyle = logArea.addStyle("Error", null);
                StyledDocument doc = logArea.getStyledDocument(); // Obtém o StyledDocument
                Style style = logArea.getStyle(StyleContext.DEFAULT_STYLE); // Usa o estilo padrão
                doc.insertString(doc.getLength(), "Erro ao fechar recursos: " + e.getMessage() + "\n", errorStyle);
            }
        }
    }

    private boolean adjustSequenceForTable(String tableName, String sequenceName) throws BadLocationException {
        String query = "SELECT setval('" + sequenceName + "', COALESCE((SELECT MAX(id) FROM " + tableName + "), 0) + 1);";

        try (Connection conn = connectionService.conectaBs();
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                Style errorStyle = logArea.addStyle("Error", null);
                StyledDocument doc = logArea.getStyledDocument(); // Obtém o StyledDocument
                Style style = logArea.getStyle(StyleContext.DEFAULT_STYLE); // Usa o estilo padrão
                doc.insertString(doc.getLength(), "Sequência ajustada com sucesso para a tabela: " + tableName + "\n", errorStyle);
                return true;  // Sucesso
            }
        } catch (SQLException e) {
            Style errorStyle = logArea.addStyle("Error", null);
            StyledDocument doc = logArea.getStyledDocument(); // Obtém o StyledDocument
            Style style = logArea.getStyle(StyleContext.DEFAULT_STYLE); // Usa o estilo padrão
            doc.insertString(doc.getLength(), "Erro ao ajustar sequência para a tabela " + tableName + "\n", errorStyle);
            e.printStackTrace();
        }
        return false;  // Falha
    }

    public static void main(String[] args) {
        // Exemplo de uso:
        SwingUtilities.invokeLater(() -> {
            try {
                Conection connectionService = new Conection();
                UpdateTablesDialog dialog = new UpdateTablesDialog(null, connectionService);
                dialog.setVisible(true);
            } catch (BadLocationException ex) {
                Logger.getLogger(UpdateTablesDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void logMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            StyledDocument doc = logArea.getStyledDocument(); // Obtém o StyledDocument
            Style style = logArea.addStyle("Style", null);

            // Verifica se a mensagem contém "Erro" ou "erro"
            if (message.contains("Erro") || message.contains("erro")) {
                StyleConstants.setForeground(style, Color.RED); // Texto em vermelho para erro
            } else {
                StyleConstants.setForeground(style, Color.BLACK); // Texto normal em preto
            }

            try {
                doc.insertString(doc.getLength(), message + "\n", style);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        });
    }

    private boolean updateTable() throws BadLocationException {
        List<String> queries = new ArrayList<>();
        // Adicionando as queries na lista
        // Adicionar colunas se não existirem
        //queries.add("DO $$ BEGIN "
        //        + "IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'canfenotfis' AND column_name = 'not_infadfisco') THEN "
        //        + "ALTER TABLE canfenotfis ADD COLUMN not_infadfisco text; "
        //        + "END IF; "
        //        + "IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'canfenotfis' AND column_name = 'not_infcpl') THEN "
        //        + "ALTER TABLE canfenotfis ADD COLUMN not_infcpl text; "
        //        + "END IF; "
        //        + "END $$;");

        // Renomear as colunas, se elas existirem
        queries.add("DO $$ BEGIN "
                + "IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'canfenotfis' AND column_name = 'not_infAdFisco' "
                + "AND NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'canfenotfis' AND column_name = 'not_infadfisco')) THEN "
                + "ALTER TABLE canfenotfis RENAME COLUMN \"not_infAdFisco\" TO \"not_infadfisco\"; "
                + "END IF; "
                + "IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'canfenotfis' AND column_name = 'not_infCpl' "
                + "AND NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'canfenotfis' AND column_name = 'not_infcpl')) THEN "
                + "ALTER TABLE canfenotfis RENAME COLUMN \"not_infCpl\" TO \"not_infcpl\"; "
                + "END IF; "
                + "END $$;");

        // Atualizar valores nas novas colunas
        //queries.add("UPDATE canfenotfis SET not_infadfisco = not_infAdFisco, not_infcpl = not_infCpl;");
        // Remover colunas se existirem
        // queries.add("DO $$ BEGIN "
        //         + "IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'canfenotfis' AND column_name = 'not_infAdFisco') THEN "
        //         + "ALTER TABLE canfenotfis DROP COLUMN not_infAdFisco; "
        //         + "END IF; "
        //         + "IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'canfenotfis' AND column_name = 'not_infcpl') THEN "
        //         + "ALTER TABLE canfenotfis DROP COLUMN not_infCpl; "
        //          + "END IF; "
        //         + "END $$;");
        // Alterar coluna se ela existir
        queries.add("DO $$ BEGIN "
                + "IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'caprodut' AND column_name = 'p_ncm') THEN "
                + "ALTER TABLE caprodut ALTER COLUMN p_ncm TYPE character(8); "
                + "END IF; "
                + "END $$;");

        // Alterar coluna se ela existir
        queries.add("DO $$ BEGIN "
                + "IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'm2pedven' AND column_name = 'ped_codi') THEN "
                + "ALTER TABLE m2pedven ALTER COLUMN ped_codi TYPE character(10); "
                + "END IF; "
                + "END $$;");

        return executeQueries(queries);
    }
}
